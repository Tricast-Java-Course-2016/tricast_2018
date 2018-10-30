package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tricast.repositories.entities.EventType;

public class EventRequest implements Serializable {

	private static final long serialVersionUID = 2889848440680242716L;

	private EventType eventTypeId;
    // Mandatory, use primitive long
	private Long leagueId;
    // For Horse Racing only, Football should be generated from the Competitors
	private String description;
    // This should be an Enum. Should only be used in an Update.
	private String status;
    // Should be OffsetDateTime
	private Date startTime;
	private List<Long> competitorIds;

	public EventType getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(EventType eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public List<Long> getCompetitorIds() {
		return competitorIds;
	}

	public void setCompetitorIds(List<Long> competitorIds) {
		this.competitorIds = competitorIds;
	}
}
