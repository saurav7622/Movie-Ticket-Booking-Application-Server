package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.dao.RoleDAO;
import com.learning.springboot_projects.movieBuzz.dao.UserDetailRepository;
import com.learning.springboot_projects.movieBuzz.dao.UserRepository;
import com.learning.springboot_projects.movieBuzz.entity.User;
import com.learning.springboot_projects.movieBuzz.entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserDetailRepository theUserDetailRepository;

    private RoleDAO theRoleDAO;


        //set up constructor injection
    @Autowired
    public MyUserDetailsService(UserDetailRepository userDetailRepository,RoleDAO roleDAO) {
            theUserDetailRepository = userDetailRepository;
            theRoleDAO = roleDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = theUserDetailRepository.findByEmail(username);

        if(user==null)
        {
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user,theRoleDAO);
    }
}
