package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.ResultType;

public interface ResultTypeManager {
	
	List<ResultType> findAll();
	
	ResultType findById(Long id);
	
	ResultType create(ResultType resulttype);
	
	ResultType update(ResultType resulttype);
	
	void deleteById(Long id);
}