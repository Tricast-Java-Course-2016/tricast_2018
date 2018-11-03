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
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.PeriodMarkets;

public class EventDetailResponseBuilder {
    
    public static EventDetailResponse build(Long id,
    		EventRepository eventRepository,
    		MarketRepository marketRepository,
    		OutcomeRepository outcomeRepository,
    		PeriodTypeRepository periodtypeRepository) {
    	
    	EventDetailResponse responseObject = new EventDetailResponse();
    	List <PeriodMarkets> periodMarketsList= new ArrayList <PeriodMarkets>();
    	List <Outcome> outcomes;
    	List <OutcomeResponse> outcomeResponses;
    	PeriodMarkets currentPeriodMarkets;
    	
    	responseObject.setEventDescription(eventRepository.findById(id).getDescription());
    	responseObject.setEventStartTime(eventRepository.findById(id).getStartTime());
    	
    	
    	for(int i=0;i<marketRepository.findByEventId(eventRepository.findById(id)).size();i++) {
    		outcomeResponses = new ArrayList <OutcomeResponse>();
    		currentPeriodMarkets=new PeriodMarkets();
    		currentPeriodMarkets.setMarket(MarketResponseMapper.mapToResponse(marketRepository.findByEventId(eventRepository.findById(id)).get(i), eventRepository));
    		currentPeriodMarkets.setPeriod(PeriodTypeResponseMapper.build(marketRepository.findByEventId(eventRepository.findById(id)).get(i).getPeriodTypeId()));
    		
    		outcomes=outcomeRepository.findByMarketId(marketRepository.findByEventId(eventRepository.findById(id)).get(i));
    		for(int j=0;j<outcomes.size();j++) {
    				outcomeResponses.add(OutcomeResponseMapper.mapToResponse(outcomes.get(j), outcomeRepository, marketRepository));
    		}
    		
    		currentPeriodMarkets.setOutcomes(outcomeResponses);	
    		periodMarketsList.add(currentPeriodMarkets);
    	}
    	
    	responseObject.setPeriodMarketsList(periodMarketsList);
    	
    	
        return responseObject;
    }

}