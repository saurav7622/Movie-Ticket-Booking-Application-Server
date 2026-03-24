package com.learning.springboot_projects.movieBuzz.dao;

import com.learning.springboot_projects.movieBuzz.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDAO {

    Ticket findById(int id);

    List<Ticket> getAllTicketsByUserId(int id);

    List<Ticket> getAllTicketsForGivenMovieByUserId(int movieId, int userId);

    List<Ticket> getAllTicketsForGivenMovieShow(int movieId);

    void save(Ticket ticket);
    void deleteByMovieId(int movie_id);
}
