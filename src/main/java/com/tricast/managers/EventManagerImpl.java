package com.tricast.managers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.builders.EventDetailResponseBuilder;
import com.tricast.builders.EventResponseBuilder;
import com.tricast.controllers.requests.EventRequest;
import com.tricast.controllers.requests.EventStatusRequest;
import com.tricast.controllers.requests.MarketForEventRequest;
import com.tricast.controllers.requests.OddsRequest;
import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.repositories.CompetitorRepository;
import com.tricast.repositories.EventCompetitorMapRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.EventTypeRepository;
import com.tricast.repositories.LeagueRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.MarketTypeRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.SportRepository;
import com.tricast.repositories.customs.EventRepositoryCustom;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.EventCompetitorMap;
import com.tricast.repositories.entities.EventStatusTypes;
import com.tricast.repositories.entities.League;
import com.tricast.repositories.entities.Market;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.Sport;

@Service
public class EventManagerImpl implements EventManager {

	@Autowired
    private EventCompetitorMapRepository eventCompetitorMapRepository;

	@Autowired
    private EventRepositoryCustom eventRepositoryCustom;

	@Autowired
    private EventRepository eventRepository;

	@Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private MarketTypeRepository marketTypeRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private PeriodTypeRepository periodTypeRepository;

    @Override
    public List<EventResponse> findAll() {
    	List<EventResponse> eventResponses = new ArrayList<>();
        for (Event event : eventRepository.findAll()) {
			eventResponses.add(EventResponseBuilder.build(event, competitorRepository.findByEventId(event.getId())));
		}
    	return eventResponses;
    }

    @Override
    public EventResponse findById(Long id) {
    	Event event = eventRepository.findById(id);
    	List<Competitor> competitor = competitorRepository.findByEventId(id);
        return EventResponseBuilder.build(event, competitor);
    }

    @Transactional
    @Override
    public EventResponse create(EventRequest eventRequest) throws SportsbookException {
    	Event event = new Event();

    	event.setDescription(eventRequest.getDescription());

    	event.setStartTime( eventRequest.getStartTime() );
    	event.setStatus(eventRequest.getStatus());
    	event.setEventType(eventTypeRepository.findById( eventRequest.getEventTypeId() ));
    	event.setLeague( leagueRepository.findOne(eventRequest.getLeagueId()) );

    	event = eventRepository.save(event);

    	List<Competitor> competitors = new ArrayList<>();
    	for (Long competitorId : eventRequest.getCompetitorIds()) {
			competitors.add(competitorRepository.findById(competitorId));

			EventCompetitorMap eventCompetitorMap = new EventCompetitorMap();
			eventCompetitorMap.setEvent(event);
			eventCompetitorMap.setCompetitorId(competitorId);

			eventCompetitorMapRepository.save(eventCompetitorMap);
		}

    	for (MarketForEventRequest marketData : eventRequest.getMarkets()) {
			Market market = new Market();
			market.setEventId(event);
            market.setMarketType(marketTypeRepository.findById(marketData.getMarketTypeId()));
			market.setPeriodTypeId(periodTypeRepository.findById( marketData.getPeriodTypeId() ));
            market.setDescription(event.getLeague().getDescription() + " - " + event.getDescription() + " - "
                    + market.getMarketType().getDescription());
			marketRepository.save(market);


            switch (market.getMarketType().getId().intValue()) {
			case Market.ID_WDW:
				if (competitors.size() != 2) {
                    throw new SportsbookException("Pontosan 2 darab csapat szerepelhet egy WDW típusú marketen!");
                }

				saveOutcome(competitors.get(0).getDescription(), "1", market);
				saveOutcome("Döntetlen", "X", market);
				saveOutcome(competitors.get(1).getDescription(), "2", market);
				break;

			case Market.ID_OUTRIGHT:
				int index = 1;
				for (Competitor competitor : competitors) {
					saveOutcome(competitor.getDescription(), Integer.toString( index++ ), market);
				}
				break;
			}

            // market type enum
            /*
             * switch (market.getMarketType().getType()) { case WDW: if (competitors.size() != 2) { throw new
             * SportsbookException("Pontosan 2 darab csapat szerepelhet egy WDW típusú marketen!"); }
             * 
             * saveOutcome(competitors.get(0).getDescription(), "1", market); saveOutcome("Döntetlen", "X", market);
             * saveOutcome(competitors.get(1).getDescription(), "2", market); break;
             * 
             * case Outright: int index = 1; for (Competitor competitor : competitors) {
             * saveOutcome(competitor.getDescription(), Integer.toString(index++), market); } break; default: break; }
             */
		}

        return EventResponseBuilder.build(event, competitors);
    }

    private void saveOutcome(String description, String outcomeCode, Market market) {
    	Outcome outcome = new Outcome();
    	outcome.setDescription(description);
    	outcome.setOutcomeCode(outcomeCode);
    	outcome.setMarket(market);
    	outcomeRepository.save(outcome);
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
			Calendar toDate) throws SportsbookException {

    	Long sportId = null;


    	if(sportName != null) {
        	Sport sport = this.sportRepository.findByDescriptionLike(sportName);
        	if(sport == null) {
        		throw new SportsbookException("Hibás sport lett megadva. ("+ sportName +")");
            }
        	sportId = sport.getId();
    	}

    	Long leagueId = null;

    	if(leagueName != null) {
	    	League league = this.leagueRepository.findByDescriptionLike(leagueName);
	    	if(league == null) {
        		throw new SportsbookException("Hibás liga lett megadva. ("+ leagueName +")");
            }
	    	leagueId = league.getId();
    	}

		List<Event> events = eventRepositoryCustom.filter( search, sportId, leagueId, fromDate, toDate );
		List<EventResponse> eventResponses = new ArrayList<>();
		for (Event event : events) {
            eventResponses.add(EventResponseBuilder.build(event, this.competitorRepository.findByEventId(event.getId())));
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

    @Override
    public List <EventResponse> listOpen(){
    	List <Event> events = eventRepositoryCustom.listOpen();
    	List <EventResponse> responses=new ArrayList <>();
    	for(Event currentEvent : events) {
    		responses.add(EventResponseBuilder.build(currentEvent, this.competitorRepository.findByEventId(currentEvent.getId())));
    	}
    	return responses;
    }

	@Override
	public EventResponse update(long id, EventStatusRequest eventStatusRequest) throws SportsbookException {
		Event event = eventRepository.findById(id);
		if(eventStatusRequest.getStatus() == EventStatusTypes.OPEN) {
			List<Outcome> outcomes = outcomeRepository.findByMarket_Event_Id(id);
			for (Outcome outcome : outcomes) {
				if (outcome.getOdds() == 0)
					throw new SportsbookException("Csak abban az esetben lehet aktívra álítani az eseményt ha minden Outcome be van állítva!");
			}
		}
		event.setStatus(eventStatusRequest.getStatus());
		return EventResponseBuilder.build(event, competitorRepository.findByEventId(id));
	}

}