package com.tricast.controllers;

import java.util.Calendar;
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

    // The $ sign has a meaning in Java, remove it.
	@GetMapping(path="/{id}")
    public EventResponse findById(@PathVariable("id") Long $id) {
        return null;
    }

    // Where was this supposed to be used? No screen seems to use.
    @GetMapping(path = "{date}/{name}")
    public List<EventResponse> findByDateAndName(@PathVariable("date") Calendar date,
            @PathVariable("name") String name) {
    	return null;
    }

    // Should be query params, both of them. Might worth merging this with the previous call.
	@GetMapping(path="/list/{search}/{page}")
    public List<EventResponse> findAll(String search ,int page) {
        return null;
    }

    // A Response would not hurt
	@PostMapping
	public void create(EventRequest eventRequest) {

	}

    // Response with the new object is mandatory!
	@PutMapping(path="/{id}")
	public void update(EventRequest eventRequest) {

	}

    @GetMapping(path = "statusList")
    public List<EventStatusResponse> getAllStatus() {
        return null;
    }

}
