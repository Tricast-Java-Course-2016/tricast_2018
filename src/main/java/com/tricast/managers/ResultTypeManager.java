package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.PeriodTypeEnum;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypeEnum;

public interface ResultTypeManager {
	
	List<ResultType> findAll();
	
	ResultType findById(Long id);
	
	ResultType create(ResultType resulttype);
	
	ResultType update(ResultType resulttype);
	
	void deleteById(Long id);
	
    List<ResultType> findByEventId(long eventId);
}