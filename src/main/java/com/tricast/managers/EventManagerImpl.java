package com.tricast.managers;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.builders.EventResponseBuilder;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.LeagueRepository;
import com.tricast.repositories.SportRepository;
import com.tricast.repositories.CompetitorRepository;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.League;
import com.tricast.repositories.entities.Sport;

@Service
public class EventManagerImpl implements EventManager {

	@Autowired
    private EventRepository EventRepository;

    @Autowired
    private SportRepository SportRepository;
    
    @Autowired
    private LeagueRepository LeagueRepository;
    
    @Autowired
    private CompetitorRepository CompetitorRepository;

    @Override
    public List<Event> findAll() {
        return EventRepository.findAll();
    }

    @Override
    public EventResponse findById(Long id) {
    	Event event = EventRepository.findById(id);
    	List<Competitor> competitor = CompetitorRepository.findByEventId(id);
        return EventResponseBuilder.build(event, competitor);
    }

    @Override
    public Event create(Event event) {
        return EventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return EventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        EventRepository.delete(id);
    }

    @Override
	public List<EventResponse> filter(String search, String sportName, String leagueName, Calendar fromDate,
			Calendar toDate) {
    	
    	long sportId = -1;
    	
    	if(sportName != null) {
        	Sport sport = this.SportRepository.findByDescriptionLike(sportName);
        	if(sport == null) return new ArrayList<EventResponse>();
        	sportId = sport.getId();
    	}
    	
    	long leagueId = -1;
    	
    	if(leagueName != null) {
	    	League league = this.LeagueRepository.findByDescriptionLike(leagueName);
	    	if(league == null) return new ArrayList<EventResponse>();
	    	leagueId = league.getId();
    	}
    	
		List<Event> events = EventRepository.filter(
				search == null ? "%" :"%"+search+"%", 
				sportId, 
				leagueId,
				fromDate,
				toDate
			);
		List<EventResponse> eventResponses = new ArrayList<EventResponse>();
		for (Event event : events) {
			eventResponses.add(EventResponseBuilder.build(event, this.CompetitorRepository.findByEventId(event.getId())));
		}
		return eventResponses;
	}

}