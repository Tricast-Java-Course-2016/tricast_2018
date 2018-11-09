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
    private EventRepository EventRepository;

    @Autowired
    private SportRepository SportRepository;

    @Autowired
    private LeagueRepository LeagueRepository;

    @Autowired
    private CompetitorRepository CompetitorRepository;

    @Autowired
    private OutcomeRepository OutcomeRepository;
    

    @Autowired
    private MarketRepository MarketRepository;

    @Autowired
    private PeriodTypeRepository PeriodTypeRepository;

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

        // AKOS inkább id-t küldeni de ha a Leaguen lenne sport akkor nem kéne külön betölteni (custom repoban)

    	if(sportName != null) {
        	Sport sport = this.SportRepository.findByDescriptionLike(sportName);
        	if(sport == null) {
                // AKOS lehet jobb lenne hibát dobni hogy ilyen nem létezik
                return new ArrayList<>();
            }
        	sportId = sport.getId();
    	}

    	long leagueId = -1;

    	if(leagueName != null) {
	    	League league = this.LeagueRepository.findByDescriptionLike(leagueName);
	    	if(league == null) {
                // AKOS lehet jobb lenne hibát dobni hogy ilyen nem létezik
                return new ArrayList<>();
            }
	    	leagueId = league.getId();
    	}

		List<Event> events = EventRepository.filter(
				search == null ? "%" :"%"+search+"%",
				sportId,
				leagueId,
				fromDate,
				toDate
			);
		List<EventResponse> eventResponses = new ArrayList<>();
		for (Event event : events) {
            eventResponses
                    .add(EventResponseBuilder.build(event, this.CompetitorRepository.findByEventId(event.getId())));
		}
		return eventResponses;
	}

    @Override
    public EventDetailResponse detail(Long id) {
    	return EventDetailResponseBuilder.build(id, EventRepository, MarketRepository, OutcomeRepository, PeriodTypeRepository);
    }
    
    @Override
    public EventDetailResponse updateOdds(OddsRequest oddsRequest) {
    	Set <Long> outcomeIds = oddsRequest.getOutcomeIdOdds().keySet();
    	Iterator <Long> iterator = outcomeIds.iterator();
    	Double currentOdds;
    	Long currentId;
    	Outcome currentOutcome;
    	
    	while(iterator.hasNext()) {
    		currentId=iterator.next();
    		currentOdds = oddsRequest.getOutcomeIdOdds().get(currentId);
    		
    		currentOutcome=OutcomeRepository.findById(currentId);
    		currentOutcome.setOdds(currentOdds);
    		
    		OutcomeRepository.save(currentOutcome);
    		
    	}
    	
    	return EventDetailResponseBuilder.build(oddsRequest.getEventId()
    			, EventRepository, MarketRepository, OutcomeRepository, PeriodTypeRepository);

    }

}