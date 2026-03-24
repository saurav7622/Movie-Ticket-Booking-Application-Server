package com.learning.springboot_projects.movieBuzz.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learning.springboot_projects.movieBuzz.entity.Movie;
import com.learning.springboot_projects.movieBuzz.entity.Seat;
import com.learning.springboot_projects.movieBuzz.entity.User;
import com.learning.springboot_projects.movieBuzz.service.MovieService;
import com.learning.springboot_projects.movieBuzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MovieRestController {

    private MovieService theMovieService;

    private ObjectMapper theObjectMapper;

    @Autowired
    public MovieRestController(MovieService movieService,ObjectMapper objectMapper){
        theMovieService = movieService;
        theObjectMapper = objectMapper;
    }

    //add mapping for GET/user/{userId}

    @GetMapping("/movie/{movieId}")
    public Movie findById(@PathVariable int movieId){
        Movie theMovie = theMovieService.findById(movieId);

        if(theMovie==null)
        {
            throw new RuntimeException("Movie id not found - "+ movieId);
        }

        return theMovie;
    }

    @GetMapping("/movies")
    public List<Movie> findAllMovies(){
        List<Movie> movies = theMovieService.findAll();

        return movies;
    }

    //add mapping for POST /user - add new employee
    @PostMapping("/movie")
    public Movie addMovie(@RequestBody Movie theMovie){

        //also in case they pass some non zero id in json .... then set id explicitly to 0
        //this is to force a save of new item instead of update

        theMovie.setId(0);

        Movie dbMovie=theMovieService.save(theMovie);
        return dbMovie;
    }

    //add mapping for PATCH /user/{userId} - patch user ......patch update
    @PatchMapping("/movie/{movieId}")
    public Movie patchMovie(@PathVariable int movieId,
                          @RequestBody Map<String,Object> patchPayload){
        Movie tempMovie = theMovieService.findById(movieId);

        //throw exception if null
        if(tempMovie==null){
            throw new RuntimeException("Movie id not found - "+movieId);
        }

        //throw exception if request body contains "id" key
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Movie id not allowed in patch payload - "+movieId);
        }

        Movie patchedMovie = apply(patchPayload,tempMovie);

        Movie dbMovie = theMovieService.save(patchedMovie);

        return dbMovie;
    }

    private Movie apply(Map<String, Object> patchPayload, Movie tempMovie) {
        //convert movie object to a json object node
        ObjectNode movieNode = theObjectMapper.convertValue(tempMovie, ObjectNode.class);

        //convert patchpayload object to a json object node
        ObjectNode patchNode = theObjectMapper.convertValue(patchPayload,ObjectNode.class);

        //Merge the patch updates into the user node
        movieNode.setAll(patchNode);

        return theObjectMapper.convertValue(movieNode,Movie.class);

    }

    //add mapping for DELETE /movie - delete a given movie
    @DeleteMapping("/movie/{movieId}")
    private void addMovie(@PathVariable int movieId){
        Movie tempMovie = theMovieService.findById(movieId);

        //throw exception if null
        if(tempMovie==null){
            throw new RuntimeException("Movie id not found - "+movieId);
        }

        theMovieService.deleteById(movieId);
    }


}
