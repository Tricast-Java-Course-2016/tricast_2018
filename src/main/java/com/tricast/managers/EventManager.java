package com.tricast.managers;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.List;

import com.tricast.controllers.requests.EventRequest;
import com.tricast.controllers.requests.EventStatusRequest;
import com.tricast.controllers.requests.OddsRequest;
import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.repositories.entities.Event;

public interface EventManager {

    List<EventResponse> findAll();

    EventResponse findById(Long id);

    EventResponse create(EventRequest event) throws SportsbookException;

    Event update(Event event);

    void deleteById(Long id);
    
    EventDetailResponse detail(Long id);

	List<EventResponse> filter(String search, String sport, String league, Calendar fromDate, Calendar toDate) throws SportsbookException;

	EventDetailResponse updateOdds(OddsRequest oddsRequest,Long eventId) throws SportsbookException;
	
	List <EventResponse> listOpen();

	EventResponse update(long id, EventStatusRequest eventStatusRequest) throws SportsbookException;
}
