package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OddsRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1334052711374875079L;

	private long eventId;
	private List<Double> odds;
	private Map<Long, Double> outcomeIdOdds;
	
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public List<Double> getOdds() {
		return odds;
	}
	public void setOdds(List<Double> odds) {
		this.odds = odds;
	}
	public Map<Long, Double> getOutcomeIdOdds() {
		return outcomeIdOdds;
	}
	public void setOutcomeIdOdds(Map<Long, Double> outcomeIdOdds) {
		this.outcomeIdOdds = outcomeIdOdds;
	}


}
