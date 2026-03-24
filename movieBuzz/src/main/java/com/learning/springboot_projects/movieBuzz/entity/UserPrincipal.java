package com.learning.springboot_projects.movieBuzz.entity;

import com.learning.springboot_projects.movieBuzz.dao.RoleDAO;
import com.learning.springboot_projects.movieBuzz.dao.RoleDAOImpl;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User user;
    private EntityManager entityManager;
    private RoleDAO theRoleDAO;


    public UserPrincipal(User user,RoleDAO roleDAO){
        this.user=user;
        theRoleDAO=roleDAO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("user.getEmail()----->"+user.getEmail());
        String roleName=theRoleDAO.getRoleByUserName(user.getEmail()).getRole().contains("ROLE_CUSTOMER")?"CUSTOMER":"ADMIN";
        System.out.println("theRoleDAO.getRoleByUserName(user.getEmail()).getRole()----->"+theRoleDAO.getRoleByUserName(user.getEmail()).getRole());
        System.out.println("roleName----->"+roleName);
        return Collections.singleton(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
