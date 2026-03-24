package com.learning.springboot_projects.movieBuzz.dao;

import com.learning.springboot_projects.movieBuzz.entity.Movie;
import com.learning.springboot_projects.movieBuzz.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    //No declaration of interface methods needed
}
