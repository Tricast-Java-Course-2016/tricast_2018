package com.tricast.controllers.requests;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.tricast.repositories.entities.EventStatusTypes;

public class EventRequest implements Serializable {

	private static final long serialVersionUID = 2889848440680242716L;

	private long eventTypeId;
	private long leagueId;
    // For Horse Racing only, Football should be generated from the Competitors
	private String description;
    // This should be an Enum. Should only be used in an Update.
	private EventStatusTypes status;
	private OffsetDateTime startTime;
	private List<Long> competitorIds;
	
	public long getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	
	public long getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(long leagueId) {
		this.leagueId = leagueId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public EventStatusTypes getStatus() {
		return status;
	}
	public void setStatus(EventStatusTypes status) {
		this.status = status;
	}
	
	public OffsetDateTime getStartTime() {
		return startTime;
	}
	@DateTimeFormat(iso = ISO.DATE_TIME)
	public void setStartTime(OffsetDateTime startTime) {
		this.startTime = startTime;
	}
	
	public List<Long> getCompetitorIds() {
		return competitorIds;
	}
	public void setCompetitorIds(List<Long> competitorIds) {
		this.competitorIds = competitorIds;
	}
}
