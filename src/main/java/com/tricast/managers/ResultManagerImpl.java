package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.requests.ResultSaveRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.controllers.responses.ResultSaveResponse;
import com.tricast.controllers.responses.ResultsByEventsResponse;
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
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.Result;
import com.tricast.repositories.entities.ResultType;

@Service
public class ResultManagerImpl implements ResultManager {
	
	private ResultRepository resultRepository;
	private EventRepository eventRepository;
	private PeriodTypeRepository periodTypeRepository;
	private ResultTypeRepository resultTypeRepository;
	private CompetitorRepository competitorRepository;
	private EventCompetitorMapRepository eventCompetitorMapRepository;

    @Autowired
	public ResultManagerImpl(
			ResultRepository resultRepository,
			EventRepository eventRepository,
			PeriodTypeRepository periodTypeRepository,
			ResultTypeRepository resultTypeRepository,
			CompetitorRepository competitorRepository,
			EventCompetitorMapRepository eventCompetitorMapRepository) {
    	this.resultRepository = resultRepository;
		this.eventRepository = eventRepository;
		this.periodTypeRepository = periodTypeRepository;
		this.resultTypeRepository = resultTypeRepository;
		this.competitorRepository = competitorRepository;
		this.eventCompetitorMapRepository = eventCompetitorMapRepository;
	}
	
	@Override
	public List<Result> findAll() {
    	return null;
	}

	@Override
	public List<ResultsByEventsResponse> findByEventId(Long eventId) {
		List<EventCompetitorMap> eventCompetitorMap = eventCompetitorMapRepository.findByEventId(eventId);
		List<Competitor> competitors = competitorRepository.findByEventId(eventId);
		List<PeriodType> periodTypes = periodTypeRepository.findAll();
		List<ResultType> resultTypes = resultTypeRepository.findAll();
		
		Event event = eventRepository.findById(eventId);
		
		List<PeriodType> periodTypesResponse = new ArrayList<PeriodType>();
		List<ResultType> resultTypesResponse = new ArrayList<ResultType>();
		
		for(PeriodType pt : periodTypes) {
			if(event.getEventType().getId() == 1) {
				if(pt.getId() != 4) {
					periodTypesResponse.add(pt);
				}
			} else {
				if(pt.getId() == 4) {
					periodTypesResponse.add(pt);
				}
			}
		}
		
		for(ResultType rt : resultTypes) {
			if(event.getEventType().getId() == 1) {
				if(rt.getId() != 3) {
					resultTypesResponse.add(rt);
				}
			} else {
				if (rt.getId() == 3) {
					resultTypesResponse.add(rt);
				}
			}
		}
		
		List<Result> result = new ArrayList<Result>();
		
		for(EventCompetitorMap currentMap : eventCompetitorMap) {
			if(resultRepository.findByEventCompetitorMap_Id(currentMap.getId()) != null) {
				result.add(resultRepository.findByEventCompetitorMap_Id(currentMap.getId()));
			}
		}
		
		List<ResultsByEventsResponse> responseObjectsList = new ArrayList<ResultsByEventsResponse>();
		ResultsByEventsResponse responseObject = new ResultsByEventsResponse();
		List<ResultResponse> resultResponseList = new ArrayList<ResultResponse>();
		
		ResultResponse resultResponse;
		
		for(Result currentResult : result) {
			resultResponse = new ResultResponse();
			resultResponse.setId(currentResult.getId());
			resultResponse.setComeptitorId(currentResult.getEventCompetitorMap().getCompetitorId());
			resultResponse.setPeriodTypeId(currentResult.getPeriodType().getId());
			resultResponse.setResultTypeId(currentResult.getResultType().getId());			
			if(currentResult.getResult() != null) {
				resultResponse.setResult(currentResult.getResult());
			}
			resultResponseList.add(resultResponse);
		}
		
		responseObject.setResultResponseList(resultResponseList);
		responseObject.setCompetitorList(competitors);
		responseObject.setPeriodResponseList(periodTypesResponse);
		responseObject.setResultTypeResponseList(resultTypesResponse);
		responseObjectsList.add(responseObject);
		
		return responseObjectsList;
	}

	@Override
	public ResultSaveResponse create(ResultSaveRequest requestObject) throws SportsbookException {
		Result result = new Result();
		ResultSaveResponse saveResponseObject = new ResultSaveResponse();
		PeriodType resultResponsePeriodType = periodTypeRepository.findById(requestObject.getResultToSave().getPeriodTypeId());
		ResultType resultResponseResultType = resultTypeRepository.findById(requestObject.getResultToSave().getResultTypeId());
		
		EventCompetitorMap eventCompetitorMap = eventCompetitorMapRepository.findByEventIdAndCompetitorId(
				requestObject.getEventId(), requestObject.getResultToSave().getCompetitorId());
		
		if(eventCompetitorMap == null) {
			throw new SportsbookException("A megadott eseményen nem játszott ez a versenyző!");
		} else {
			saveResponseObject.setEventCompetitorMapId(eventCompetitorMap.getId());
			result.setEventCompetitorMap(eventCompetitorMap);
		}
		
		Event event = eventRepository.findById(eventCompetitorMap.getEventId());		

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
		
		saveResponseObject.setResponseToSave(this.entityToResponse(result));
		return saveResponseObject;
	}

	@Override
	public void deleteById(Long id) {
		resultRepository.delete(id);
	}
	
	private ResultResponse entityToResponse(Result result) {
 		
     	ResultResponse response = new ResultResponse();
 		
 		response.setId(result.getId());
 		response.setResultTypeId(result.getResultType().getId());
 		response.setResult(result.getResult());
 		response.setPeriodTypeId(result.getPeriodType().getId());
     		
 		return response;
 	}

	@Override
	public ResultSaveResponse update(long resultId, ResultSaveRequest resultUpdate) {
		Result resultToSave = new Result();
		ResultResponse currentResult = resultRepository.findById(resultId);
		
		currentResult.setResultTypeId(resultUpdate.getResultToSave().getResultTypeId());
		currentResult.setResult(resultUpdate.getResultToSave().getResult());
		currentResult.setPeriodTypeId(resultUpdate.getResultToSave().getPeriodTypeId());
		
//		resultToSave.setResultType(resultType);
		
		return null;
	}
		
}
