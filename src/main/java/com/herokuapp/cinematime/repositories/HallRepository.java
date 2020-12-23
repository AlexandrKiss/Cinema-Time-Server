package com.herokuapp.cinematime.repositories;

import com.herokuapp.cinematime.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HallRepository extends JpaRepository<Hall, Long> {
    @Modifying
    @Query(value = "alter sequence dev.hall_id_seq RESTART with 1", nativeQuery = true)
    void restartAutoIncrement();

    @Modifying
    @Query(value = "delete from dev.hall", nativeQuery = true)
    void deleteAll();
}
