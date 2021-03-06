package com.tricast.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.ResultTypeManager;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypeEnum;

@RestController
@RequestMapping(path = "api/resulttypes")
public class ResultTypeController {

	@Autowired
	private ResultTypeManager resultTypeManager;

	@GetMapping
	public List<ResultType> findAll() {
		return resultTypeManager.findAll();
	}
	
    @GetMapping(path = "findByEventId/{id}")
    public List<ResultType> findByEventId(@PathVariable("id") long eventId){
    	List<ResultTypeEnum> resultTypeEnumList = resultTypeManager.findByEventId(eventId);
    	List<ResultType> resultTypes = new ArrayList<ResultType>();
    	
    	ResultType resultType;
    	
    	for(ResultTypeEnum currentResultTypeEnum : resultTypeEnumList) {
    		resultType = new ResultType();
    		resultType.setId(currentResultTypeEnum.getId());
    		resultType.setDescription(currentResultTypeEnum.getDescription());
    		resultType.setType(currentResultTypeEnum);
    		resultTypes.add(resultType);
    	}
    	return resultTypes;
    }
}
