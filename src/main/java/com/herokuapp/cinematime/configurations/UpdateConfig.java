package com.herokuapp.cinematime.configurations;

import com.herokuapp.cinematime.model.*;
import com.herokuapp.cinematime.repositories.VkinoRepository;
import com.herokuapp.cinematime.services.CinemaService;
import com.herokuapp.cinematime.services.MovieService;
import com.herokuapp.cinematime.services.SessionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class UpdateConfig {
    final private CinemaService cinemaService;
    final private MovieService movieService;
    final private SessionService sessionService;
    final private VkinoRepository vkinoRepository;

    public UpdateConfig(CinemaService cinemaService,
                        MovieService movieService,
                        SessionService sessionService,
                        VkinoRepository vkinoRepository) {
        this.cinemaService = cinemaService;
        this.movieService = movieService;
        this.sessionService = sessionService;
        this.vkinoRepository = vkinoRepository;
    }

    @Bean
    public CommandLineRunner firstRun() {
        return strings -> updateFilmsList();
    }

    @Scheduled(cron = "${scheduler.cron}")
    void updateFilmsList() {
        sessionService.deleteAll();
        movieService.deleteAll();
        cinemaService.deleteAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        List<Movie> movies = vkinoRepository.getFilmsList();

        if (movies==null) {
            return;
        }

        Hall hall = new Hall();
        hall = cinemaService.addHall(hall);

        Type economy = new Type("Economy", 80.0D);
        Type standard = new Type("Standard", 120.0D);
        Type premium = new Type("Premium", 200.0D);
        cinemaService.addType(Arrays.asList(economy, standard, premium));

        List<Seat> seats = new ArrayList<>();

        for (Movie movie: movies) {
            movieService.addMovie(movie);

            DateSession dateSession = new DateSession(dateFormat.format(new Date()), movie);
            sessionService.addDateSession(dateSession);

            sessionService.addSession(new Session(timeFormat.format(new Date()), hall, dateSession));
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 2; j++) {
                switch (i){
                    case 1: seats.add(new Seat(j, i, hall, economy));
                        break;
                    case 2: seats.add(new Seat(j, i, hall, standard));
                        break;
                    case 3: seats.add(new Seat(j, i, hall, premium));
                        break;
                }
            }
        }
        cinemaService.addSeat(seats);
    }
}
