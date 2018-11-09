package com.tricast.managers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.builders.EventDetailResponseBuilder;
import com.tricast.builders.EventResponseBuilder;
import com.tricast.controllers.requests.OddsRequest;
import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.repositories.CompetitorRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.LeagueRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.SportRepository;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.League;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.Sport;

@Service
public class EventManagerImpl implements EventManager {

    // AKOS: kis kezdőbetűs nevek
	@Autowired
    private EventRepository eventRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private OutcomeRepository outcomeRepository;
    

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private PeriodTypeRepository periodTypeRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public EventResponse findById(Long id) {
    	Event event = eventRepository.findById(id);
    	List<Competitor> competitor = competitorRepository.findByEventId(id);
        return EventResponseBuilder.build(event, competitor);
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.delete(id);
    }

    @Override
	public List<EventResponse> filter(String search, String sportName, String leagueName, Calendar fromDate,
			Calendar toDate) {

    	long sportId = -1;

        // AKOS inkább id-t küldeni de ha a Leaguen lenne sport akkor nem kéne külön betölteni (custom repoban)

    	if(sportName != null) {
        	Sport sport = this.sportRepository.findByDescriptionLike(sportName);
        	if(sport == null) {
                // AKOS lehet jobb lenne hibát dobni hogy ilyen nem létezik
                return new ArrayList<>();
            }
        	sportId = sport.getId();
    	}

    	long leagueId = -1;

    	if(leagueName != null) {
	    	League league = this.leagueRepository.findByDescriptionLike(leagueName);
	    	if(league == null) {
                // AKOS lehet jobb lenne hibát dobni hogy ilyen nem létezik
                return new ArrayList<>();
            }
	    	leagueId = league.getId();
    	}

		List<Event> events = eventRepository.filter(
				search == null ? "%" :"%"+search+"%",
				sportId,
				leagueId,
				fromDate,
				toDate
			);
		List<EventResponse> eventResponses = new ArrayList<>();
		for (Event event : events) {
            eventResponses
                    .add(EventResponseBuilder.build(event, this.competitorRepository.findByEventId(event.getId())));
		}
		return eventResponses;
	}

    @Override
    public EventDetailResponse detail(Long id) {
    	return EventDetailResponseBuilder.build(id, eventRepository, marketRepository, outcomeRepository, periodTypeRepository);
    }
    
    @Override
    public EventDetailResponse updateOdds(OddsRequest oddsRequest,Long eventId) throws SportsbookException {
    	Set <Long> outcomeIds = oddsRequest.getOutcomeIdOdds().keySet();
    	Iterator <Long> iterator = outcomeIds.iterator();
    	Double currentOdds;
    	Long currentId;
    	Outcome currentOutcome;
    	

        while(iterator.hasNext()) {
        	currentId=iterator.next();
        	if(outcomeRepository.findById(currentId)==null) {
        		throw new SportsbookException("Bad OutcomeIds.");
        	}
        	else {
            	if(eventId != marketRepository.findById(outcomeRepository.findById(currentId).getMarket().getId()).getEvent().getId()) {
        			throw new SportsbookException("Bad OutcomeIds, some of them do not belong to the event you're trying to edit: ("+eventId+").");
            	}
        	}
        }
    	
	iterator = outcomeIds.iterator();
    	while(iterator.hasNext()) {
    		currentId=iterator.next();
    		currentOdds = oddsRequest.getOutcomeIdOdds().get(currentId);
    		
    		currentOutcome=outcomeRepository.findById(currentId);
    		currentOutcome.setOdds(currentOdds);
    		
    		outcomeRepository.save(currentOutcome);
    		
    	}
    	
    	return EventDetailResponseBuilder.build(oddsRequest.getEventId()
    			, eventRepository, marketRepository, outcomeRepository, periodTypeRepository);

    }

}