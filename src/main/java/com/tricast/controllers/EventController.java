package com.tricast.controllers;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.EventRequest;
import com.tricast.controllers.requests.OddsRequest;
import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.controllers.responses.EventStatusResponse;
import com.tricast.managers.EventManager;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.managers.helpers.OffsetDateTimeToCalendar;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(path = "api/events")
public class EventController {

    @Autowired
    private EventManager eventManager;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@GetMapping(path="/{id}")
    public EventResponse findById(@PathVariable("id") long id) {
		return eventManager.findById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
    @GetMapping(path = "/filter")
    public List<EventResponse> byFilter(
    		@RequestParam(value = "sport", required = false) String sport,
            @RequestParam(value = "league", required = false) String league,
            @RequestParam(value = "fromDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fromDate,
            @RequestParam(value = "toDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime toDate,
            @RequestParam(value = "search", required = false) String search) throws SportsbookException {


    	return this.eventManager.filter(search, sport, league, OffsetDateTimeToCalendar.convert(fromDate), OffsetDateTimeToCalendar.convert(toDate));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
    @GetMapping(path = "/{id}/detail")
    public EventDetailResponse detail(@PathVariable("id") long id) {
    	return eventManager.detail(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@PostMapping
	public EventResponse create(EventRequest eventRequest) {
		return eventManager.create(eventRequest);
	}

	@PutMapping(path="/{id}")
	public EventResponse update(long id, EventRequest eventRequest) {
		return null;
	}

    @GetMapping(path = "statusList")
    public List<EventStatusResponse> getAllStatus() {
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
    @GetMapping(path = "/{id}/odds")
    public EventDetailResponse showOdds(@PathVariable("id") long id) {
    	return eventManager.detail(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
    @PostMapping(path="/{id}/odds")
	public ResponseEntity<?> updateOdds(@RequestBody OddsRequest oddsRequest,@PathVariable("id") long id) {
		try {
			if(oddsRequest.getEventId()!=id) {
                throw new SportsbookException("URL, EventID mismatch.");
            }
			EventDetailResponse response = eventManager.updateOdds(oddsRequest,id);
			return ResponseEntity.ok(response);
		} catch (SportsbookException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
