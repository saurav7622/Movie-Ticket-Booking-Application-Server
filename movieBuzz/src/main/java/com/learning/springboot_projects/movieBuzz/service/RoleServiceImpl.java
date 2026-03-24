package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.dao.RoleDAO;
import com.learning.springboot_projects.movieBuzz.entity.Role;
import com.learning.springboot_projects.movieBuzz.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO theRoleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO){
        theRoleDAO = roleDAO;
    }
    public Role getRoleByUserName(String username)
    {
        return theRoleDAO.getRoleByUserName(username);
    }

    @Override
    @Transactional
    public void save(Role role) {
        theRoleDAO.save(role);
    }
}
