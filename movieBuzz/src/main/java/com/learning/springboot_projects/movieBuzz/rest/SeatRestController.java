package com.learning.springboot_projects.movieBuzz.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.springboot_projects.movieBuzz.entity.Seat;
import com.learning.springboot_projects.movieBuzz.entity.User;
import com.learning.springboot_projects.movieBuzz.service.SeatService;
import com.learning.springboot_projects.movieBuzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SeatRestController {

    private SeatService theSeatService;


    @Autowired
    public SeatRestController(SeatService seatService){
        theSeatService = seatService;
    }

    //add mapping for GET/user/{userId}

    @GetMapping("/seats")
    public List<Seat> findAllSeats(){
        List<Seat> seats = theSeatService.findAll();

        return seats;
    }
}
