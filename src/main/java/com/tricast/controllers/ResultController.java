package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.ResultRequest;
import com.tricast.managers.ResultManager;
import com.tricast.repositories.entities.Result;

@RestController
@RequestMapping(path = "results")
public class ResultController {

    @Autowired
    private ResultManager resultManager;

    @GetMapping(path = "{id}")
    public Result findById(@PathVariable("id") Long id) {
        return null;
    }
    	
    @PostMapping
    public void  createResult(ResultRequest resultRequest) {
    }
	
}
