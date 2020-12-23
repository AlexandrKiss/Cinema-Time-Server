package com.herokuapp.cinematime.repositories;

import com.herokuapp.cinematime.model.Hall;
import com.herokuapp.cinematime.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> getByHall(Hall hall);

    @Modifying
    @Query(value = "alter sequence dev.seat_id_seq RESTART with 1", nativeQuery = true)
    void restartAutoIncrement();

    @Modifying
    @Query(value = "delete from dev.seat", nativeQuery = true)
    void deleteAll();
}
