package com.learning.springboot_projects.movieBuzz.dao;

import com.learning.springboot_projects.movieBuzz.entity.Role;
import com.learning.springboot_projects.movieBuzz.entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private EntityManager theEntityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager){
        theEntityManager = entityManager;
    }
    public Role getRoleByUserName(String Username){
        String jpql = "FROM Role r WHERE username = :Username";
        TypedQuery<Role> query = theEntityManager.createQuery(jpql, Role.class);
        query.setParameter("Username", Username);
        Role role= query.getSingleResult();

        return role;
    }

    public void save(Role role){
        theEntityManager.persist(role);
    }
}
