package com.tricast.controllers.responses;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import com.tricast.repositories.entities.EventStatusTypes;

public class EventResponse implements Serializable {

	private static final long serialVersionUID = 4538977295592317848L;

    // Use primitive
	private long id;
	private long eventTypeId;
    // Use primitive
	private long leagueId;
	private String description;
    // Use Enum
	private EventStatusTypes status;
    // Use OffsetDateTime
	private OffsetDateTime startTime;
    // Full list of competitors would be better
	private List<Long> competitorIds;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
