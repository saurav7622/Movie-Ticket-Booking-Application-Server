package com.learning.springboot_projects.movieBuzz.service;

import com.learning.springboot_projects.movieBuzz.dao.MovieRepository;
import com.learning.springboot_projects.movieBuzz.dao.TicketDAO;
import com.learning.springboot_projects.movieBuzz.entity.Movie;
import com.learning.springboot_projects.movieBuzz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository theMovieRepository;

    private TicketDAO theTicketDAO;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,TicketDAO ticketDAO){
        theMovieRepository = movieRepository;
        theTicketDAO = ticketDAO;
    }
    @Override
    public List<Movie> findAll() {

        return theMovieRepository.findAll();
    }

    @Override
    public Movie findById(int id) {
        Optional<Movie> result= theMovieRepository.findById(id);

        Movie theMovie = null;
        if(result.isPresent()) {
            theMovie = result.get();
        }
        else {
            throw new RuntimeException("Did not find movie id of " + id);
        }

        return theMovie;
    }

    @Override
    public Movie save(Movie movie) {
        return theMovieRepository.save(movie);
    }

    @Override
    public void deleteById(int id) {
        theTicketDAO.deleteByMovieId(id);
        theMovieRepository.deleteById(id);


    }
}
