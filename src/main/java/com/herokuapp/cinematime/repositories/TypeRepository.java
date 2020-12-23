package com.herokuapp.cinematime.repositories;

import com.herokuapp.cinematime.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type, Long> {
    @Modifying
    @Query(value = "alter sequence dev.type_id_seq RESTART with 1", nativeQuery = true)
    void restartAutoIncrement();

    @Modifying
    @Query(value = "delete from dev.type", nativeQuery = true)
    void deleteAll();
}
