package com.tricast.managers;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.List;

import com.tricast.controllers.requests.OddsRequest;
import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.repositories.entities.Event;

public interface EventManager {

    List<Event> findAll();

    EventResponse findById(Long id);

    Event create(Event event);

    Event update(Event event);

    void deleteById(Long id);
    
    EventDetailResponse detail(Long id);

	List<EventResponse> filter(String search, String sport, String league, Calendar fromDate, Calendar toDate);

	EventDetailResponse updateOdds(OddsRequest oddsRequest) throws SportsbookException;
}
