package cz.hybernia.ass.dao;

import cz.hybernia.ass.data.Student;
import cz.hybernia.ass.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.StudentsDao.getAllStudents}")
    private String getAllStudentsQuery;

    @Value("${queries.StudentsDao.getStudent}")
    private String getStudentQuery;

    public List<Student> getAllStudents() {
        return jdbcTemplate.query(getAllStudentsQuery, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student getStudent(int id) {
        try {
            return jdbcTemplate.queryForObject(getStudentQuery, new Object[]{id}, new BeanPropertyRowMapper<>(Student.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Student id=" + id + " doesn't exist");
        }
    }
}
