package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.dao.TicketDAO;
import com.learning.springboot_projects.movieBuzz.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketDAO theTicketDAO;

    @Autowired
    public TicketServiceImpl(TicketDAO ticketDAO){
        theTicketDAO = ticketDAO;
    }

    @Override
    public Ticket findById(int id) {
        return theTicketDAO.findById(id);
    }

    @Override
    public List<Ticket> getAllTicketsByUserId(int id) {
        return theTicketDAO.getAllTicketsByUserId(id);
    }

    @Override
    public List<Ticket> getAllTicketsForGivenMovieByUserId(int movieId, int userId) {
        return theTicketDAO.getAllTicketsForGivenMovieByUserId(movieId, userId);
    }

    @Override
    public List<Ticket> getAllTicketsForGivenMovieShow(int movieId) {
        return theTicketDAO.getAllTicketsForGivenMovieShow(movieId);
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        theTicketDAO.save(ticket);
    }
}
