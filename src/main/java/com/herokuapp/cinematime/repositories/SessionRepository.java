package com.herokuapp.cinematime.repositories;

import com.herokuapp.cinematime.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Modifying
    @Query(value = "alter sequence dev.session_id_seq RESTART with 1", nativeQuery = true)
    void restartAutoIncrement();

    @Modifying
    @Query(value = "delete from dev.session", nativeQuery = true)
    void deleteAll();
}
