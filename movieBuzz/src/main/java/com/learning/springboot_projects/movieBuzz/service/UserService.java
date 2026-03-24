package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findById(int id);

    User save(User user,String roleString);

    String verify(User user);
}
