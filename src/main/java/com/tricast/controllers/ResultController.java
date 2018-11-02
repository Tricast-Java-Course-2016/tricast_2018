package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.ResultSaveRequest;
import com.tricast.controllers.responses.ResultResponse;
import com.tricast.managers.ResultManager;

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
    public void createResults(ResultSaveRequest resultRequest) {
    }


}
