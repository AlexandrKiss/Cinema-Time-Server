package com.herokuapp.cinematime.repositories;

import com.herokuapp.cinematime.model.DateSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DateSessionRepository extends JpaRepository<DateSession, Long> {
    @Modifying
    @Query(value = "alter sequence dev.date_session_id_seq RESTART with 1", nativeQuery = true)
    void restartAutoIncrement();

    @Modifying
    @Query(value = "delete from dev.date_session", nativeQuery = true)
    void deleteAll();
}
