package com.tricast.managers;

import java.util.List;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.repositories.entities.Result;

public interface ResultManager {
	
    List<Result> findAll();

    List<ResultResponse> findById(Long id);

    ResultResponse create(ResultRequest requestObject);

    Result update(Result result);

    void deleteById(Long id);
    
}
