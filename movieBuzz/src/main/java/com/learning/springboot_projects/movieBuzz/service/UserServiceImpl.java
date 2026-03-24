package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.dao.RoleDAO;
import com.learning.springboot_projects.movieBuzz.dao.UserRepository;
import com.learning.springboot_projects.movieBuzz.entity.Role;
import com.learning.springboot_projects.movieBuzz.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    //define field for entity manager
    private UserRepository theUserRepository;

    private PasswordEncoder passwordEncoder;

    private RoleDAO theRoleDAO;

    private AuthenticationManager theAuthManager;

    private JWTService theJWTService;


    //set up constructor injection
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleDAO roleDAO,AuthenticationManager authManager,
                           JWTService jWTService) {
            this.theUserRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            theRoleDAO = roleDAO;
            theAuthManager = authManager;
            theJWTService = jWTService;
        }

    @Override
    public User findById(int id) {
        Optional<User> result= theUserRepository.findById(id);

        User theUser = null;
        if(result.isPresent()) {
            theUser = result.get();
        }
        else {
            throw new RuntimeException("Did not find user id of " + id);
        }

        return theUser;
    }

    @Override
    //role->"ROLE_CUSTOMER" for normal user but role->"ROLE_ADMIN" for admin user
    @Transactional
    public User save(User user,String roleString) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        User dbUser = theUserRepository.save(user);

        Role role = new Role(user.getEmail(),roleString);
        theRoleDAO.save(role);


        return dbUser;
    }

    @Override
    public String verify(User user) {
        Authentication authentication = theAuthManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

        if(authentication.isAuthenticated())
            return theJWTService.generateToken(user.getEmail());

        return "fail";
    }


}
