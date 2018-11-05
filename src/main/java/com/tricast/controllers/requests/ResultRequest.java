package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;

public class ResultRequest implements Serializable {

	private static final long serialVersionUID = -6817451528421899276L;

    private long resultTypeId;
    private int result;
    private long periodTypeId;
    
    private List<Event> events;
    private List<Competitor> competitors;
	public long getResultTypeId() {
		return resultTypeId;
	}
	public void setResultTypeId(long resultTypeId) {
		this.resultTypeId = resultTypeId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public long getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(long periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Competitor> getCompetitors() {
		return competitors;
	}
	public void setCompetitors(List<Competitor> competitors) {
		this.competitors = competitors;
	}
    
    
}
