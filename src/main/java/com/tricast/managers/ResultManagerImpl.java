package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.managers.mappers.ResultResponseMapper;
import com.tricast.repositories.EventCompetitorMapRepository;
import com.tricast.repositories.ResultRepository;
import com.tricast.repositories.entities.EventCompetitorMap;
import com.tricast.repositories.entities.Result;
import com.tricast.repositories.entities.ResultType;

@Service
public class ResultManagerImpl implements ResultManager {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
	private EventCompetitorMapRepository eventCompetitorMapRepository;
	
	@Override
	public List<Result> findAll() {
		return resultRepository.findAll();
	}

	@Override
	public List<ResultResponse> findById(Long id) {
		
		List<EventCompetitorMap> evenetCompetitorMapList = eventCompetitorMapRepository.findByEventId(id);
		List<ResultResponse> resultResponses = new ArrayList<ResultResponse>();
		
		for (Result result : resultRepository.findAll()) {
			
			for (EventCompetitorMap ecm : evenetCompetitorMapList) {
				if(ecm.getId() == result.getEventCompetitorMapId()) {
					resultResponses.add(this.entityToResponse(result));
				}
			}
		}
		
		return resultResponses;
	}

	@Override
	public ResultResponse create(ResultRequest requestObject) {
		Result result = new Result();
		
		//Mi alapján lesznek majd validálva?
		result.setResultTypeId(requestObject.getResultTypeId());
		result.setResult(requestObject.getResult());
		result.setPeriodTypeId(requestObject.getPeriodTypeId());
		result.setEventCompetitorMapId(requestObject.getEventCompetitorMapId());
		
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
		
    	ResultResponse response = new ResultResponse();
		
		response.setId(result.getId());
		response.setResultTypeId(result.getResultTypeId());
		response.setResult(result.getResult());
		response.setPeriodTypeId(result.getPeriodTypeId());
		response.setEventCompetitorMapId(result.getEventCompetitorMapId());
    		
		return response;
	}
	
	
}
