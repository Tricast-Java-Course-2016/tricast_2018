package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.controllers.requests.ResultSaveRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.controllers.responses.ResultSaveResponse;
import com.tricast.controllers.responses.ResultsByEventsResponse;
import com.tricast.managers.ResultManager;

@RestController
@RequestMapping(path = "api")
public class ResultController {

    @Autowired
    private ResultManager resultManager;

    @GetMapping(path = "events/{id}/results")
    public List <ResultsByEventsResponse> findByEventId(@PathVariable("id") long eventId) {
        return resultManager.findByEventId(eventId);
    }

    @PostMapping
    public ResponseEntity<?> createResults(ResultSaveRequest newResult) {
    	try {
    		ResultSaveResponse result = resultManager.create(newResult);
			return ResponseEntity.ok(result);
    	}catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    	}
    }
}
