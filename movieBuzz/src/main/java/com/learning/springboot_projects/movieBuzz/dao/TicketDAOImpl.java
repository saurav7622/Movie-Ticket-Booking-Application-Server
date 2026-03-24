package com.learning.springboot_projects.movieBuzz.dao;

import com.learning.springboot_projects.movieBuzz.entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    private EntityManager theEntityManager;

    @Autowired
    public TicketDAOImpl(EntityManager entityManager){
        theEntityManager = entityManager;
    }
    @Override
    public Ticket findById(int id) {
        Ticket ticket = theEntityManager.find(Ticket.class,id);

        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketsByUserId(int user_id) {
        String jpql = "FROM Ticket t WHERE userId = :userId";
        TypedQuery<Ticket> query = theEntityManager.createQuery(jpql, Ticket.class);
        query.setParameter("userId", user_id);
        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

    @Override
    public List<Ticket> getAllTicketsForGivenMovieByUserId(int movie_id, int user_id) {
        String jpql = "FROM Ticket t WHERE userId = :userid AND movieId = :movieid";
        TypedQuery<Ticket> query = theEntityManager.createQuery(jpql, Ticket.class);
        query.setParameter("userid", user_id);
        query.setParameter("movieid", movie_id);
        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

    @Override
    public List<Ticket> getAllTicketsForGivenMovieShow(int movie_id) {
        String jpql = "FROM Ticket t WHERE movieId = :movieId";
        TypedQuery<Ticket> query = theEntityManager.createQuery(jpql, Ticket.class);
        query.setParameter("movieId", movie_id);
        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

    public void save(Ticket ticket){
        theEntityManager.persist(ticket);
    }

    public void deleteByMovieId(int movie_id)
    {
        String jpql = "DELETE FROM Ticket t WHERE t.movieId = :movie_id";

        int deletedCount = theEntityManager.createQuery(jpql)
                .setParameter("movie_id", movie_id)
                .executeUpdate();
    }

}
