package com.herokuapp.cinematime.services;

import com.herokuapp.cinematime.model.DateSession;
import com.herokuapp.cinematime.model.Session;

import java.util.List;

public interface SessionService {
    //Session CRUD
    Session addSession(Session session);
    Session getSession(Long id);
    Session updateSession(Session session);
    List<Session> getAllSessions();

    //DateSession CRUD
    DateSession addDateSession(DateSession dateSession);
    DateSession getDateSession(Long id);
    DateSession updateDateSession(DateSession dateSession);
    List<DateSession> getAllDateSessions();

    void deleteAll();
}
