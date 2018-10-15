package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.ResultTypes;

public interface ResultTypesManager {
	
	List<ResultTypes> findAll();
	
	ResultTypes findById(Long id);
	
	ResultTypes create(ResultTypes resulttype);
	
	ResultTypes update(ResultTypes resulttype);
	
	void deleteById(Long id);
}