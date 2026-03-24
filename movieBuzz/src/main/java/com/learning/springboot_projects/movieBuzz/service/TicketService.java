package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    Ticket findById(int id);

    List<Ticket> getAllTicketsByUserId(int id);

    List<Ticket> getAllTicketsForGivenMovieByUserId(int movieId, int userId);

    List<Ticket> getAllTicketsForGivenMovieShow(int movieId);

    void save(Ticket ticket);
}
