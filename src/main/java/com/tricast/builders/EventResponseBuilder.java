package com.tricast.builders;

import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.EventResponse;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;

public class EventResponseBuilder {

	public static EventResponse build(Event event, List<Competitor> competitors) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getId());
        eventResponse.setEventTypeId(event.getEventTypeId().getId());
        eventResponse.setDescription(event.getDescription());
        eventResponse.setLeagueId(event.getLeagueId().getId());
        eventResponse.setStartTime(event.getStartTime());
        eventResponse.setStatus(event.getStatus());
        List<Long> competitorIds = new ArrayList<>();

        // AKOS jobb lenne olyan adatokat is visszaküldeni amit még meg akarunk jeleníteni a résztvevőkről
        // (description mondjuk?)
        for (Competitor competitor : competitors) {
			competitorIds.add(competitor.getId());
		}
        eventResponse.setCompetitorIds(competitorIds);
		return eventResponse;
    }

}
