package com.herokuapp.cinematime.services;

import com.herokuapp.cinematime.model.Hall;
import com.herokuapp.cinematime.model.Seat;
import com.herokuapp.cinematime.model.Type;

import java.util.List;

public interface CinemaService {
    //Hall CRUD
    Hall addHall();
    Hall addHall(Hall hall);
    Hall getHall(Long id);

    //Seat CRUD
    Seat addSeat(Seat seat);
    public void addSeat(List<Seat> seats);
    Seat getSeat(Long id);
    List<Seat> getListSeatsInHall(Long hallId);

    //Type CRUD
    Type addType(Type type);
    void addType(List<Type> types);
    Type getType(Long id);
    Type updateType(Type type);

    void deleteAll();
}
