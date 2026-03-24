package com.learning.springboot_projects.movieBuzz.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learning.springboot_projects.movieBuzz.entity.Movie;
import com.learning.springboot_projects.movieBuzz.entity.Ticket;
import com.learning.springboot_projects.movieBuzz.service.MovieService;
import com.learning.springboot_projects.movieBuzz.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TicketRestController {
    private TicketService theTicketService;

    private ObjectMapper theObjectMapper;

    @Autowired
    public TicketRestController(TicketService ticketService,ObjectMapper objectMapper){
        theTicketService = ticketService;
        theObjectMapper = objectMapper;
    }

    //add mapping for GET/user/{userId}

    @GetMapping("/ticket/{ticketId}")
    public Ticket findTicketById(@PathVariable int ticketId){
        Ticket theTicket = theTicketService.findById(ticketId);

        if(theTicket==null)
        {
            throw new RuntimeException("Ticket id not found - "+ ticketId);
        }

        return theTicket;
    }

    @GetMapping("/getTicketsByUserId/{userId}")
    public List<Ticket> findAllTicketsByUserId(@PathVariable int userId){
        List<Ticket> tickets = theTicketService.getAllTicketsByUserId(userId);

        return tickets;
    }

    @GetMapping("/tickets/{movieId}/{userId}")
    public List<Ticket> findAllTicketsForGivenMovieByUserId(@PathVariable int movieId,
                                                            @PathVariable int userId){

        List<Ticket> tickets = theTicketService.getAllTicketsForGivenMovieByUserId(movieId,userId);

        return tickets;
    }
    @GetMapping("/getTicketsByMovieId/{movieId}")
    List<Ticket> findAllTicketsForGivenMovie(@PathVariable int movieId){

        List<Ticket> tickets = theTicketService.getAllTicketsForGivenMovieShow(movieId);

        return tickets;
    }

    //add mapping for POST /ticket - add new ticket
    @PostMapping("/ticket")
    public void addTicket(@RequestBody Ticket theTicket){



        //also in case they pass some non zero id in json .... then set id explicitly to 0
        //this is to force a save of new item instead of update

        theTicket.setId(0);

        theTicketService.save(theTicket);
    }
}
