package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.controllers.responses.ResultResponse;
import com.tricast.repositories.entities.Result;

public interface ResultRepository extends CrudRepository<Result, Long> {

	@Override
    List<Result> findAll();

	Result findById(long id);
		
	List<Result> findByEventCompetitorMap_Event_Id(long eventId);
	
}