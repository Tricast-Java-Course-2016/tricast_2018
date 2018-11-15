package com.tricast.repositories.customs;

import java.util.Calendar;
import java.util.List;

import com.tricast.repositories.entities.Event;

public interface EventRepositoryCustom {

    List<Event> filter(String search, Long sportId, Long leagueId, Calendar fromDate, Calendar toDate);
    
    List <Event> listOpen();
}
