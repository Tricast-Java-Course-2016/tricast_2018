package com.tricast.managers.mappers;

import com.tricast.controllers.responses.MarketResponse;
import com.tricast.repositories.BetRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.entities.Market;

public class MarketResponseMapper {

	  public static MarketResponse mapToResponse(Market entityObject, 
			  	BetRepository betRepository, 
	    		EventRepository eventRepository) {
	        if(entityObject == null) {
	            return null;
	        }

	        MarketResponse responseObject = new MarketResponse();
	        responseObject.setMarketDescription(entityObject.getDescription());
	        responseObject.setMarketTypeDescription(entityObject.getMarketTypeId().getDescription().getValue());
	        responseObject.setPeriodTypeDescription(entityObject.getPeriodTypeId().getDescription().getValue());
	        responseObject.setStatus(eventRepository.findById(entityObject.getEventId().getId()).getStatus());
	        responseObject.setEventDescription(eventRepository.findById(entityObject.getEventId().getId()).getDescription());
	        
	        return responseObject;

	  }
	
}
