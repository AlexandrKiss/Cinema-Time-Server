package com.herokuapp.cinematime.repositories;

import com.herokuapp.cinematime.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Modifying
    @Query(value = "alter sequence dev.movie_id_seq RESTART with 1", nativeQuery = true)
    void restartAutoIncrement();

    @Modifying
    @Query(value = "delete from dev.movie", nativeQuery = true)
    void deleteAll();
}
