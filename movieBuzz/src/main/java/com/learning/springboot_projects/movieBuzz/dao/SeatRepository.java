package com.learning.springboot_projects.movieBuzz.dao;

import com.learning.springboot_projects.movieBuzz.entity.Seat;
import com.learning.springboot_projects.movieBuzz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    //No declaration of interface methods needed
}
