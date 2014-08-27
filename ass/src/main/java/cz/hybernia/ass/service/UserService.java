package cz.hybernia.ass.service;

import cz.hybernia.ass.dao.UsersDao;
import cz.hybernia.ass.data.User;
import cz.hybernia.ass.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    @Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        return loadUserByUsername(token.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser;

        try {
            dbUser = usersDao.getUserByName(username);
        } catch (ResourceNotFoundException ex) {
            throw new UsernameNotFoundException("User '" + username + "' doesn't exist.");
        }

        List<String> roles = usersDao.getUserRolesById(dbUser.getId());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for (String role: roles) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(role));
        }

        return  new org.springframework.security.core.userdetails.User(
                dbUser.getUserName(),
                dbUser.getPassword(),
                grantedAuthorityList
        );
    }
}
