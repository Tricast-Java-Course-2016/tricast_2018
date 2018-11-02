package com.tricast.controllers;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.EventRequest;
import com.tricast.controllers.responses.EventDetailResponse;
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
    public EventResponse findById(@PathVariable("id") long id) {
        return null;
    }

    @GetMapping(path = "/filter/")
    public List<EventResponse> byFilter(@RequestParam(value = "sport", required = false) String sport,
            @RequestParam(value = "league", required = false) String league,
            @RequestParam(value = "fromDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fromDate,
            @RequestParam(value = "toDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime toDate,
            @RequestParam(value = "search", required = false) String search) {
        return null;
    }

    @GetMapping(path = "/{id}/detail")
    public EventDetailResponse detail(@PathVariable("id") long id) {
    	return eventManager.detail(id);
    }

    // A Response would not hurt
	@PostMapping
	public EventResponse create(EventRequest eventRequest) {
		return null;
	}

    // Response with the new object is mandatory!
	@PutMapping(path="/{id}")
	public EventResponse update(long id, EventRequest eventRequest) {
		return null;
	}

    @GetMapping(path = "statusList")
    public List<EventStatusResponse> getAllStatus() {
        return null;
    }

}
