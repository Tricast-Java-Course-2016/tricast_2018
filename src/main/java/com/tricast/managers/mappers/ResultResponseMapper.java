package com.tricast.managers.mappers;

import java.util.List;

import com.tricast.controllers.responses.ResultResponse;
import com.tricast.repositories.CompetitorRepository;
import com.tricast.repositories.EventCompetitorMapRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.ResultTypeRepository;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.EventCompetitorMap;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.Result;

public class ResultResponseMapper {

//	public ResultResponse entityToResponse(Result result) {
//		
//		ResultResponse response = new ResultResponse();
//		response.setId(result.getId());
//		response.setResultTypeId(result.getResultTypeId());
//		response.setResult(result.getResult());
//		response.setPeriodTypeId(result.getPeriodTypeId());
//		response.setEventCompetitorMapId(result.getEventCompetitorMapId());
//		return response;
//		return null;
//		
//	}
	
//	public static ResultResponse eventToResultMap(
//			Result entityObject,
//			//EventRepository eventRepository,
//			PeriodTypeRepository periodTypeRepository,
//			ResultTypeRepository resultTypeRepository,
//			CompetitorRepository competitorRepository,
//			EventCompetitorMapRepository eventCompetitorMapRepository,
//			long eventId) {
//		
//		if (entityObject == null) {
//			return null;
//		}
//		
//		ResultResponse resultResponseObject = new ResultResponse();
//		
//		//Event event = eventRepository.findById(eventId);
//		List<Competitor> competitors = competitorRepository.findByEventId(eventId);
//		List<EventCompetitorMap> eventCompetitorMap = eventCompetitorMapRepository.findByEventId(eventId);
//		//List<PeriodType> periodTypeList = 
//		
//		resultResponseObject.setPeriodTypeId(entityObject.getPeriodTypeId());
//		resultResponseObject.setResultTypeId(entityObject.getResultTypeId());
//		resultResponseObject.setResult(entityObject.getResult());
//		
//		if(entityObject.getResult() != null) {
//			resultResponseObject.setResult(entityObject.getResult());
//		}
//		
//		return resultResponseObject;
//	}
	
}
