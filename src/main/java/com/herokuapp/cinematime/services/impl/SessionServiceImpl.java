package com.herokuapp.cinematime.services.impl;

import com.herokuapp.cinematime.model.DateSession;
import com.herokuapp.cinematime.model.Session;
import com.herokuapp.cinematime.repositories.DateSessionRepository;
import com.herokuapp.cinematime.repositories.SessionRepository;
import com.herokuapp.cinematime.services.SessionService;
import com.herokuapp.cinematime.utils.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SessionServiceImpl extends Logging implements SessionService {
    private final SessionRepository sessionRepository;
    private final DateSessionRepository dateSessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, DateSessionRepository dateSessionRepository) {
        this.sessionRepository = sessionRepository;
        this.dateSessionRepository = dateSessionRepository;
    }

    //Session CRUD
    @Override
    public Session addSession(Session session) {
        Session result = sessionRepository.save(session);
        super.log("IN addSession - session: {} successfully added", result);
        return result;
    }

    @Override
    public Session getSession(Long id) {
        Session result = sessionRepository.findById(id).orElse(null);
        if(result == null) {
            log.warn("IN getSession - no session found by ID: {}", id);
            throw new NullPointerException();
        }
        super.log("IN getSession - session: {}", result);
        return result;
    }

    @Override
    public Session updateSession(Session session) {
        Session result = sessionRepository.findById(session.getId()).orElse(null);
        if(result == null) {
            log.warn("IN updateSession - no session found by ID: {}", session.getId());
            throw new NullPointerException();
        }
        sessionRepository.save(result);
        super.log("IN updateSession - session: {} successfully updated", result);
        return result;
    }

    @Override
    public List<Session> getAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        super.log("IN getAllSessions - {} sessions found", sessions.size());
        return sessions;
    }

    //DateSession CRUD
    @Override
    public DateSession addDateSession(DateSession dateSession) {
        DateSession result = dateSessionRepository.save(dateSession);
        super.log("IN addDateSession - dateSession: {} successfully added", result);
        return result;
    }

    @Override
    public DateSession getDateSession(Long id) {
        DateSession result = dateSessionRepository.findById(id).orElse(null);
        if(result == null) {
            log.warn("IN getDateSession - no dateSession found by ID: {}", id);
            throw new NullPointerException();
        }
        super.log("IN getDateSession - dateSession: {}", result);
        return result;
    }

    @Override
    public DateSession updateDateSession(DateSession dateSession) {
        DateSession result = dateSessionRepository.findById(dateSession.getId()).orElse(null);
        if(result == null) {
            log.warn("IN updateDateSession - no dateSession found by ID: {}", dateSession.getId());
            throw new NullPointerException();
        }
        dateSessionRepository.save(result);
        super.log("IN updateDateSession - dateSession: {} successfully updated", result);
        return result;
    }

    @Override
    public List<DateSession> getAllDateSessions() {
        List<DateSession> dateSessions = dateSessionRepository.findAll();
        super.log("IN getAllDateSessions - {} dateSessions found", dateSessions.size());
        return dateSessions;
    }

    @Transactional
    @Override
    public void deleteAll() {
        sessionRepository.deleteAll();
        dateSessionRepository.deleteAll();
        dateSessionRepository.restartAutoIncrement();
        sessionRepository.restartAutoIncrement();
    }
}
