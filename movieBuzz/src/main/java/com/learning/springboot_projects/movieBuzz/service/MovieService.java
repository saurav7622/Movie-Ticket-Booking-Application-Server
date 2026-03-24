package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    List<Movie> findAll();

    Movie findById(int id);

    Movie save(Movie movie);

    void deleteById(int id);
}
