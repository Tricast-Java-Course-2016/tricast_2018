package com.tricast.managers.mappers;

import com.tricast.controllers.responses.ResultResponse;
import com.tricast.repositories.entities.Result;

public class ResultResponseMapper {

	public ResultResponse entityToResponse(Result result) {
		ResultResponse response = new ResultResponse();
		response.setId(result.getId());
		response.setResultTypeId(result.getResultTypeId());
		response.setResult(result.getResult());
		response.setPeriodTypeId(result.getPeriodTypeId());
		response.setEventCompetitorMapId(result.getEventCompetitorMapId());
		return response;
	}
	
}
