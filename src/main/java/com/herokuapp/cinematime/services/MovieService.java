package com.herokuapp.cinematime.services;

import com.herokuapp.cinematime.model.Movie;

import java.util.List;

public interface MovieService {
    //Movie CRUD
    Movie addMovie(String name, String genre, int duration, String img, String description, String trailer);
    Movie addMovie(Movie movie);
    Movie getMovie(Long id);
    Movie updateMovie(Movie movie);
    List<Movie> getAllMovies();

    void deleteAll();
}
