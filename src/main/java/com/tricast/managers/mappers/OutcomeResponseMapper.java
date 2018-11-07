package com.tricast.managers.mappers;

import com.tricast.controllers.responses.OutcomeResponse;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.entities.Outcome;

public class OutcomeResponseMapper {
	public static OutcomeResponse mapToResponse(Outcome entityObject, 
			OutcomeRepository outcomeRepository,
			MarketRepository marketRepository){
        if(entityObject == null) {
            return null;
        }
	
        OutcomeResponse responseObject = new OutcomeResponse();
        
        responseObject.setOutcomeDescription(entityObject.getDescription());
        responseObject.setOdds(entityObject.getOdds());
        responseObject.setOutcomeId(entityObject.getId());
        
        return responseObject;
	}
}