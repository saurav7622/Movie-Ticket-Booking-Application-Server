package com.learning.springboot_projects.movieBuzz.dao;

import com.learning.springboot_projects.movieBuzz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<User,Integer> {

    User findByEmail(String username);
}
