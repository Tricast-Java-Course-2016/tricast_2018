package com.tricast.managers.mappers;

import com.tricast.controllers.responses.MarketResponse;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.entities.Market;

public class MarketResponseMapper {

	  public static MarketResponse mapToResponse(Market entityObject,
	    		EventRepository eventRepository) {
	        if(entityObject == null) {
	            return null;
	        }

	        MarketResponse responseObject = new MarketResponse();
	        responseObject.setMarketId(entityObject.getId());
	        responseObject.setMarketDescription(entityObject.getDescription());
        responseObject.setMarketTypeDescription(entityObject.getMarketType().getType().getDescription());
	        responseObject.setStatus(eventRepository.findById(entityObject.getEvent().getId()).getStatus().getValue());

	        return responseObject;

	  }

}
