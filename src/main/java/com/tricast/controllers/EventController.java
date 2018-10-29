package com.tricast.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.EventRequest;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.controllers.responses.EventStatusResponse;
import com.tricast.managers.EventManager;

@RestController
@RequestMapping(path = "events")
public class EventController {

    @Autowired
    private EventManager eventManager;

	@GetMapping(path="/{id}")
    public EventResponse findById(@PathVariable("id") Long $id) {
        return null;
    }
	
	@GetMapping(path="/list/{search}/{page}")
    public List<EventResponse> findAll(String search ,int page) {
        return null;
    } 
	
	@PostMapping
	public void create(EventRequest eventRequest) {
		
	}
	
	@PutMapping(path="/{id}")
	public void update(EventRequest eventRequest) {
		
	}
    
    @GetMapping(path = "statusList")
    public List<EventStatusResponse> getAllStatus() {
        return null;
    }

}
