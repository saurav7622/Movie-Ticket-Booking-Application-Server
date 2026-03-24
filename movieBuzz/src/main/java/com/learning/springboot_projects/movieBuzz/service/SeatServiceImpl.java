package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.dao.SeatRepository;
import com.learning.springboot_projects.movieBuzz.entity.Seat;
import com.learning.springboot_projects.movieBuzz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService{

    private SeatRepository theSeatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository){
        theSeatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAll() {
        return theSeatRepository.findAll();
    }
}
