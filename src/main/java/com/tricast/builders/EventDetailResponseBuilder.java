package com.tricast.builders;

import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.entities.PeriodMarkets;

public class EventDetailResponseBuilder {
    
    public static EventDetailResponse build(Long id,
    		EventRepository eventRepository,
    		MarketRepository marketRepository,
    		OutcomeRepository outcomeRepository,
    		PeriodTypeRepository periodtypeRepository) {
    	
    	EventDetailResponse responseObject = new EventDetailResponse();
    	List <PeriodMarkets> periodMarketsList= new ArrayList <PeriodMarkets>();
    	PeriodMarkets currentPeriodMarkets;
    	
    	responseObject.setEventDescription(eventRepository.findById(id).getDescription());
    	//responseObject.setEventStartTime(eventRepository.findById(id).getStartTime());
    	
    	
    	for(int i=0;i<marketRepository.findByEventId(eventRepository.findById(id)).size();i++) {
    		currentPeriodMarkets=new PeriodMarkets();
    		currentPeriodMarkets.setMarket(marketRepository.findByEventId(eventRepository.findById(id)).get(i));
    		currentPeriodMarkets.setPeriod(marketRepository.findByEventId(eventRepository.findById(id)).get(i).getPeriodTypeId());
    		
    		currentPeriodMarkets.setOutcomes(outcomeRepository.findByMarketId(marketRepository.findByEventId(eventRepository.findById(id)).get(i)));	
    		periodMarketsList.add(currentPeriodMarkets);
    	}
    	
    	responseObject.setPeriodMarketsList(periodMarketsList);
    	
    	
        return responseObject;
    }

}