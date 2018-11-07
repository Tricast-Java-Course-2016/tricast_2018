package com.tricast.builders;

import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.controllers.responses.OutcomeResponse;
import com.tricast.managers.mappers.MarketResponseMapper;
import com.tricast.managers.mappers.OutcomeResponseMapper;
import com.tricast.managers.mappers.PeriodTypeResponseMapper;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.Market;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.PeriodMarkets;

public class EventDetailResponseBuilder {

    public static EventDetailResponse build(Long id,
    		EventRepository eventRepository,
    		MarketRepository marketRepository,
    		OutcomeRepository outcomeRepository,
    		PeriodTypeRepository periodtypeRepository) {

    	EventDetailResponse responseObject = new EventDetailResponse();
    	List <PeriodMarkets> periodMarketsList= new ArrayList <>();
    	List <Outcome> outcomes;
    	List <OutcomeResponse> outcomeResponses;
    	PeriodMarkets currentPeriodMarkets;
    	List <Market> markets;
    	
    	Event currentEvent=eventRepository.findById(id);

    	responseObject.setEventDescription(currentEvent.getDescription());
    	responseObject.setEventId(currentEvent.getId());
    	responseObject.setEventStartTime(currentEvent.getStartTime().toInstant().toEpochMilli());

    	markets=marketRepository.findByEvent_Id(currentEvent.getId());
    	
    	for(Market market : markets) {
    		outcomeResponses = new ArrayList <>();
    		currentPeriodMarkets=new PeriodMarkets();

    		currentPeriodMarkets.setMarket(MarketResponseMapper.mapToResponse(market, eventRepository));
    		currentPeriodMarkets.setPeriod(PeriodTypeResponseMapper.build(market.getPeriodTypeId()));

    		outcomes=outcomeRepository.findByMarket_Id(market.getId());
    		for(Outcome outcome : outcomes) {
    				outcomeResponses.add(OutcomeResponseMapper.mapToResponse(outcome, outcomeRepository, marketRepository));
    		}

    		currentPeriodMarkets.setOutcomes(outcomeResponses);
    		periodMarketsList.add(currentPeriodMarkets);
    	}

    	responseObject.setPeriodMarketsList(periodMarketsList);


        return responseObject;
    }

}