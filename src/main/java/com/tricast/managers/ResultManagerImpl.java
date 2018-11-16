package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.responses.PeriodResponse;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.controllers.responses.ResultTypeResponse;
import com.tricast.controllers.responses.ResultsByEventsResponse;
import com.tricast.managers.mappers.ResultResponseMapper;
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
import com.tricast.repositories.entities.PeriodTypes;
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
			resultResponse.setPeriodTypeId(currentResult.getPeriodTypeId().getId());
			resultResponse.setResultTypeId(currentResult.getResultTypeId().getId());			
			resultResponse.setResult(currentResult.getResult());
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
	public ResultResponse create(ResultRequest requestObject) {
		Result result = new Result();
		
		//Mi alapján lesznek majd validálva?
		result.setResultTypeId(requestObject.getResultTypeId());
		result.setResult(requestObject.getResult());
		result.setPeriodTypeId(requestObject.getPeriodTypeId());
		//result.setEventCompetitorMapId(requestObject.getEventCompetitorMapId());
		
		result = resultRepository.save(result);
		
		ResultResponse resultResponse = this.entityToResponse(result);
				
		return resultResponse;
	}

	@Override
	public Result update(Result result) {
		return resultRepository.save(result);
	}

	@Override
	public void deleteById(Long id) {
		resultRepository.delete(id);
	}
	
    private ResultResponse entityToResponse(Result result) {
//		
//    	ResultResponse response = new ResultResponse();
//		
//		response.setId(result.getId());
//		response.setResultTypeId(result.getResultTypeId());
//		response.setResult(result.getResult());
//		response.setPeriodTypeId(result.getPeriodTypeId());
//		response.setEventCompetitorMapId(result.getEventCompetitorMapId());
//    		
//		return response;
    	return null;
	}
	
	
}
