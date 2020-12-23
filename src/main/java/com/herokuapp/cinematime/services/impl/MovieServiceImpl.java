package com.herokuapp.cinematime.services.impl;

import com.herokuapp.cinematime.model.Movie;
import com.herokuapp.cinematime.repositories.MovieRepository;
import com.herokuapp.cinematime.services.MovieService;
import com.herokuapp.cinematime.utils.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl extends Logging implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie addMovie(String name, String genre, int duration, String img, String description, String trailer) {
        Movie result = movieRepository.save(
                new Movie(name, genre, duration, img, description, trailer));
        super.log("IN addMovie - movie: {} successfully added", result);
        return result;
    }

    @Override
    public Movie addMovie(Movie movie) {
        Movie result = movieRepository.save(movie);
        super.log("IN addMovie - movie: {} successfully added", result);
        return result;
    }

    @Override
    public Movie getMovie(Long id) {
        Movie result = movieRepository.findById(id).orElse(null);
        if(result == null) {
            log.warn("IN getMovie - no movie found by ID: {}", id);
            throw new NullPointerException();
        }
        super.log("IN getMovie - movie: {}", result);
        return result;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie result = movieRepository.findById(movie.getId()).orElse(null);
        if(result == null) {
            log.warn("IN updateMovie - no session found by ID: {}", movie.getId());
            throw new NullPointerException();
        }
        movieRepository.save(result);
        super.log("IN updateMovie - session: {} successfully updated", result);
        return result;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        super.log("IN getAllMovies - {} movies found", movies.size());
        return movies;
    }

    @Transactional
    @Override
    public void deleteAll() {
        movieRepository.deleteAll();
        movieRepository.restartAutoIncrement();
    }
}
