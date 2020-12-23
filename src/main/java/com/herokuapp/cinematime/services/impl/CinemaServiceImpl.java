package com.herokuapp.cinematime.services.impl;

import com.herokuapp.cinematime.model.Hall;
import com.herokuapp.cinematime.model.Seat;
import com.herokuapp.cinematime.model.Type;
import com.herokuapp.cinematime.repositories.HallRepository;
import com.herokuapp.cinematime.repositories.SeatRepository;
import com.herokuapp.cinematime.repositories.TypeRepository;
import com.herokuapp.cinematime.services.CinemaService;
import com.herokuapp.cinematime.utils.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CinemaServiceImpl extends Logging implements CinemaService {
    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;
    private final TypeRepository typeRepository;

    public CinemaServiceImpl(HallRepository hallRepository, SeatRepository seatRepository, TypeRepository typeRepository) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.typeRepository = typeRepository;
    }

    //Hall CRUD
    @Override
    public Hall addHall() {
        Hall hall = new Hall();
        Hall result = hallRepository.save(hall);
        super.log("IN addHall - hall: {} successfully added", result);
        return result;
    }

    @Override
    public Hall addHall(Hall hall) {
        Hall result = hallRepository.save(hall);
        super.log("IN addHall - hall: {} successfully added", result);
        return result;
    }

    @Override
    public Hall getHall(Long id) {
        Hall result = hallRepository.findById(id).orElse(null);
        if(result == null) {
            log.warn("IN getHall - no hall found by ID: {}", id);
            throw new NullPointerException();
        }
        super.log("IN getHall - hall: {}", result);
        return result;
    }

    //Seat CRUD
    @Override
    public Seat addSeat(Seat seat) {
        Seat result = seatRepository.save(seat);
        super.log("IN addSeat - seat: {} successfully added", result);
        return result;
    }

    @Override
    public void addSeat(List<Seat> seats) {
        List <Seat> result = seatRepository.saveAll(seats);
        super.log("IN addSeat - seat: {} successfully added", result.size());
    }

    @Override
    public Seat getSeat(Long id) {
        Seat result = seatRepository.findById(id).orElse(null);
        if(result == null) {
            log.warn("IN getSeat - no seat found by ID: {}", id);
            throw new NullPointerException();
        }
        super.log("IN getSeat - seat: {}", result);
        return result;
    }

    @Override
    public List<Seat> getListSeatsInHall(Long hallId) {
        Hall hall = hallRepository.findById(hallId).orElse(null);
        if(hall == null) {
            log.warn("IN getListSeatsInHall - no hall found by ID: {}", hallId);
            throw new NullPointerException();
        }
        List<Seat> seatsByHall = seatRepository.getByHall(hall);
        super.log("IN getListSeatsInHall - {} seats found", seatsByHall.size());
        return seatsByHall;
    }

    //Type CRUD
    @Override
    public Type addType(Type type) {
        Type result = typeRepository.save(type);
        super.log("IN addType - type: {} successfully added", result);
        return result;
    }

    @Override
    public void addType(List<Type> types) {
        List<Type> result = typeRepository.saveAll(types);
        super.log("IN addType - type: {} successfully added", result.size());
    }

    @Override
    public Type getType(Long id) {
        Type result = typeRepository.findById(id).orElse(null);
        if(result == null) {
            log.warn("IN getType - no type found by ID: {}", id);
            throw new NullPointerException();
        }
        super.log("IN getType - type: {}", result);
        return result;
    }

    @Override
    public Type updateType(Type type) {
        Type result = typeRepository.findById(type.getId()).orElse(null);
        if(result == null) {
            log.warn("IN updateType - no type found by ID: {}", type.getId());
            throw new NullPointerException();
        }
        typeRepository.save(result);
        super.log("IN updateType - type: {} successfully updated", result);
        return result;
    }

    @Transactional
    @Override
    public void deleteAll() {
        seatRepository.deleteAll();
        typeRepository.deleteAll();
        hallRepository.deleteAll();
        hallRepository.restartAutoIncrement();
        seatRepository.restartAutoIncrement();
        typeRepository.restartAutoIncrement();
    }
}
