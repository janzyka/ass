package cz.hybernia.ass.dao;

import cz.hybernia.ass.data.User;
import cz.hybernia.ass.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersDao {

    private final JdbcTemplate jdbcTemplate;

    @Value("${queries.UsersDao.getUserByName}")
    private String userByNameQuery;

    @Value("${queries.UsersDao.getUserRoles}")
    private String userRolesById;

    @Autowired
    public UsersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserByName(String name) {
        try {
            return this.jdbcTemplate.queryForObject(userByNameQuery, new Object[]{name}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("User name=" + name + " doesn't exist");
        }
    }

    public List<String> getUserRolesById(int userId) {
        return this.jdbcTemplate.queryForList(userRolesById, new Object[]{userId}, String.class);
    }
}
