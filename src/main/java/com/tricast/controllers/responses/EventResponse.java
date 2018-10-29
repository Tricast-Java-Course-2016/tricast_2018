package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tricast.repositories.entities.EventType;

public class EventResponse implements Serializable {

	private static final long serialVersionUID = 4538977295592317848L;

	private Long id;
	private EventType eventTypeId;
	private Long leagueId;
	private String description; 
	private String status;
	private Date startTime;
	private List<Long> competitorIds;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
