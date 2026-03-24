package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    List<Seat> findAll();
}
