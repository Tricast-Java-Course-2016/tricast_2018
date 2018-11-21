package com.tricast.managers;

import java.util.List;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.requests.ResultSaveRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.controllers.responses.ResultSaveResponse;
import com.tricast.controllers.responses.ResultsByEventsResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.repositories.entities.Result;

public interface ResultManager {
	
    List<Result> findAll();

    List<ResultsByEventsResponse> findByEventId(Long eventId);

    ResultSaveResponse create(ResultSaveRequest requestObject) throws SportsbookException;

    ResultSaveResponse update(long resultId, ResultSaveRequest resultUpdate);

    void deleteById(Long id);
    
}
