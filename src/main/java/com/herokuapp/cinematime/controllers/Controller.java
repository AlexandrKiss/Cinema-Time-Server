package com.herokuapp.cinematime.controllers;

import com.herokuapp.cinematime.model.DateSession;
import com.herokuapp.cinematime.model.Hall;
import com.herokuapp.cinematime.model.Movie;
import com.herokuapp.cinematime.model.Session;
import com.herokuapp.cinematime.services.CinemaService;
import com.herokuapp.cinematime.services.MovieService;
import com.herokuapp.cinematime.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class Controller {
    private final CinemaService cinemaService;
    private final SessionService sessionService;
    private final MovieService movieService;;

    public Controller(CinemaService cinemaService,
                      SessionService sessionService,
                      MovieService movieService) {
        this.cinemaService = cinemaService;
        this.sessionService = sessionService;
        this.movieService = movieService;
    }

    @GetMapping("hall/get/{id}")
    public Hall getHall(@PathVariable("id") long id) {
        try {
            return cinemaService.getHall(id);
        } catch (NullPointerException npe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Hall with ID: '" + id + "'");
        }
    }

    @GetMapping("session/get/{id}")
    public Session getSession(@PathVariable("id") long id) {
        try {
            return sessionService.getSession(id);
        } catch (NullPointerException npe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Session with ID: '" + id + "'");
        }
    }

    @GetMapping("date/get/{id}")
    public DateSession getDateSession(@PathVariable("id") long id) {
        try {
            return sessionService.getDateSession(id);
        } catch (NullPointerException npe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No DateSession with ID: '" + id + "'");
        }
    }

    @GetMapping("movie/get/{id}")
    public Movie getMovie(@PathVariable("id") long id) {
        try {
            return movieService.getMovie(id);
        } catch (NullPointerException npe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No DateSession with ID: '" + id + "'");
        }
    }

    @GetMapping("movie/all")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }
}