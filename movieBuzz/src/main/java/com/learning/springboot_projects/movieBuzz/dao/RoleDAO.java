package com.learning.springboot_projects.movieBuzz.dao;

import com.learning.springboot_projects.movieBuzz.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO {
    Role getRoleByUserName(String username);

    void save(Role role);
}
