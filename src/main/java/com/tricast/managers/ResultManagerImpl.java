package com.tricast.managers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.requests.ResultSaveRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.controllers.responses.ResultSaveResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.repositories.CompetitorRepository;
import com.tricast.repositories.EventCompetitorMapRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.ResultRepository;
import com.tricast.repositories.ResultTypeRepository;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.EventCompetitorMap;
import com.tricast.repositories.entities.EventTypeEnum;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.PeriodTypeEnum;
import com.tricast.repositories.entities.Result;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypeEnum;
import com.tricast.repositories.entities.SportEnum;

@Service
public class ResultManagerImpl implements ResultManager {
	
	private ResultRepository resultRepository;
	private EventRepository eventRepository;
	private PeriodTypeRepository periodTypeRepository;
	private ResultTypeRepository resultTypeRepository;
	private CompetitorRepository competitorRepository;
	private EventCompetitorMapRepository eventCompetitorMapRepository;

	private ResultTypeManager resultTypeManager;
	private PeriodTypeManager periodTypeManager;
	
	private Set<Competitor> competitors = new HashSet<Competitor>();
	
    @Autowired
	public ResultManagerImpl(
			ResultRepository resultRepository,
			EventRepository eventRepository,
			PeriodTypeRepository periodTypeRepository,
			ResultTypeRepository resultTypeRepository,
			CompetitorRepository competitorRepository,
			EventCompetitorMapRepository eventCompetitorMapRepository,
			ResultTypeManager resultTypeManager,
			PeriodTypeManager periodTypeManager) {
    	this.resultRepository = resultRepository;
		this.eventRepository = eventRepository;
		this.periodTypeRepository = periodTypeRepository;
		this.resultTypeRepository = resultTypeRepository;
		this.competitorRepository = competitorRepository;
		this.eventCompetitorMapRepository = eventCompetitorMapRepository;
		this.resultTypeManager = resultTypeManager;
		this.periodTypeManager = periodTypeManager;
	}
	
	@Override
	public List<Result> findAll() {
    	return null;
	}

	@Override
	public List<ResultResponse> findByEventId(long eventId) {
		
		List<ResultResponse> listOfResultResponse = new ArrayList<ResultResponse>();
		ResultResponse resultResponse;
		
		List<Result> resultByEventId = resultRepository.findByEventCompetitorMap_Event_IdOrderByEventCompetitorMap_CompetitorIdAscResultType_IdAscPeriodType_IdAsc(eventId);
		
		for(Result currentResult : resultByEventId) {
			resultResponse = new ResultResponse();
			resultResponse.setId(currentResult.getId());
			resultResponse.setResultTypeId(currentResult.getResultType().getId());
			resultResponse.setResult(currentResult.getResult());
			resultResponse.setPeriodTypeId(currentResult.getPeriodType().getId());
			resultResponse.setComeptitorId(currentResult.getEventCompetitorMap().getCompetitorId());
			listOfResultResponse.add(resultResponse);
		}

		return listOfResultResponse;
	}

	@Override
	public ResultSaveResponse create(ResultSaveRequest requestObject) throws SportsbookException {
		Result result = new Result();
		Event event = eventRepository.findById(requestObject.getEventId());
		ResultSaveResponse saveResponseObject = new ResultSaveResponse();
		PeriodType resultResponsePeriodType = periodTypeRepository.findById(requestObject.getResultToSave().getPeriodTypeId());
		ResultType resultResponseResultType = resultTypeRepository.findById(requestObject.getResultToSave().getResultTypeId());
		Competitor competitor = competitorRepository.findById(requestObject.getResultToSave().getCompetitorId());
		
		if(event.getEventType().getType() == EventTypeEnum.RANKEVENT) {
				if(competitors.contains(competitor)) {
					throw new SportsbookException("Nem adhat meg egy versenyen ugyanazon versenyzőhöz különböző eredményt!");
				} else {
					competitors.add(competitor);
				}
		}
		
		EventCompetitorMap eventCompetitorMap = eventCompetitorMapRepository.findByEventIdAndCompetitorId(
				requestObject.getEventId(), requestObject.getResultToSave().getCompetitorId());
		
		if(eventCompetitorMap == null) {
			throw new SportsbookException("A megadott eseményen nem játszott ez a versenyző!");
		} else {
			saveResponseObject.setEventCompetitorMapId(eventCompetitorMap.getId());
			result.setEventCompetitorMap(eventCompetitorMap);
		}

		if(event.getEventType().getId() == 1) {
			if(resultResponsePeriodType.getId() != 4 && resultResponseResultType.getId() != 3) {
					result.setPeriodType(resultResponsePeriodType);
					result.setResultType(resultResponseResultType);
			} else {
				throw new SportsbookException("Nem megfelelő a periódus, vagy az eredmény típusa!\nGameEvent-hez kell, hogy tartozzon!");
			}
		} else {
			if(resultResponsePeriodType.getId() == 4 && resultResponseResultType.getId() == 3) {
				result.setPeriodType(resultResponsePeriodType);
				result.setResultType(resultResponseResultType);
			} else {
				throw new SportsbookException("Nem megfelelő a periódus, vagy az eredmény típusa!\nRankEvent-hez kell, hogy tartozzon!");
			}
		}
		result.setResult(requestObject.getResultToSave().getResult());
		result = resultRepository.save(result);
		
		saveResponseObject.setResponseToSave(this.entityToResponse(result, requestObject.getResultToSave().getCompetitorId()));
		return saveResponseObject;
	}

	@Override
	public void deleteById(Long id) {
		resultRepository.delete(id);
	}
	
	private ResultResponse entityToResponse(Result result, long competitorId) {
 		
     	ResultResponse response = new ResultResponse();
 		
 		response.setId(result.getId());
 		response.setResultTypeId(result.getResultType().getId());
 		response.setResult(result.getResult());
 		response.setPeriodTypeId(result.getPeriodType().getId());
     	response.setComeptitorId(competitorId);
 		return response;
 	}
	
	@Override
	public ResultSaveResponse update(long resultId, ResultSaveRequest resultRequestToUpdate) throws SportsbookException {
		
		List<ResultTypeEnum> resultTypesByEvent = resultTypeManager.findByEventId(resultRequestToUpdate.getEventId());
		List<PeriodTypeEnum> periodTypesByEvent = periodTypeManager.findByEventId(resultRequestToUpdate.getEventId());
		
		ResultType resultResponseResultType = resultTypeRepository.findById(resultRequestToUpdate.getResultToSave().getResultTypeId());
		PeriodType resultResponsePeriodType = periodTypeRepository.findById(resultRequestToUpdate.getResultToSave().getPeriodTypeId());
		
		ResultSaveResponse resultSaveResponse = new ResultSaveResponse();
		
		Result resultToUpdate = resultRepository.findById(resultId);
		EventCompetitorMap currentEventCompetitorMap = eventCompetitorMapRepository.findByEventIdAndCompetitorId(resultRequestToUpdate.getEventId(), resultRequestToUpdate.getResultToSave().getCompetitorId());

		if(currentEventCompetitorMap == null) {
			throw new SportsbookException("A megadott eseményen nem játszott ez a versenyző!");
		} else {
			resultSaveResponse.setEventCompetitorMapId(currentEventCompetitorMap.getId());
			resultToUpdate.setEventCompetitorMap(currentEventCompetitorMap);
		}
		
		if(resultTypesByEvent.contains(resultResponseResultType.getType())) {
			resultToUpdate.setResultType(resultResponseResultType);
		} else {
			throw new SportsbookException("Nem megfelelő az eredmény típusa!");
		}
		
		if(periodTypesByEvent.contains(resultResponsePeriodType.getType())) {
			resultToUpdate.setPeriodType(resultResponsePeriodType);
		} else {
			throw new SportsbookException("Nem megfelelő a periódus típusa!");
		}
		
		resultToUpdate.setResult(resultRequestToUpdate.getResultToSave().getResult());
		
		resultToUpdate = resultRepository.save(resultToUpdate);
		
		resultSaveResponse.setResponseToSave(this.entityToResponse(resultToUpdate, resultRequestToUpdate.getResultToSave().getCompetitorId()));
		
		return resultSaveResponse;
	}
}
