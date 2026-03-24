package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.entity.Role;

public interface RoleService {

    Role getRoleByUserName(String username);

    void save(Role role);
}
