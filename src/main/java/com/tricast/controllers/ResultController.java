package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.requests.ResultSaveRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.managers.ResultManager;
import com.tricast.repositories.entities.Result;

@RestController
@RequestMapping(path = "results")
public class ResultController {

    @Autowired
    private ResultManager resultManager;

    // Instead of this, get a list of results for the screen, by eventId.
    @GetMapping(path = "{id}")
    public List <ResultResponse> findById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping
    public ResultResponse createResults(ResultRequest newResult) {
    	Result result = new Result();
    	result.setResultTypeId(newResult.getResultTypeId());
    	result.setResult(newResult.getResult());
    	result.setPeriodTypeId(newResult.getPeriodTypeId());
    	result.setEventCompetitorMapId(newResult.getEventCompetitorMapId());
    	
    	result = resultManager.create(result);
    	
		ResultResponse response = new ResultResponse();
    	response.setId(result.getId());
    	response.setResultTypeId(result.getResultTypeId());
    	response.setResult(result.getResult());
    	response.setPeriodTypeId(result.getPeriodTypeId());
    	response.setEventCompetitorMapId(result.getEventCompetitorMapId());
    	
    	return response;
    }


}
