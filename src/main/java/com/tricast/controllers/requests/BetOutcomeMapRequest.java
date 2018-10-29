package com.tricast.controllers.requests;

import java.io.Serializable;

public class BetOutcomeMapRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4027065991077356896L;
	
	private Long betId;
	private Long outcomeId;
	private Double odds;
	
	public Long getBetId() {
		return betId;
	}
	public void setBetId(Long betId) {
		this.betId = betId;
	}
	public Long getOutcomeId() {
		return outcomeId;
	}
	public void setOutcomeId(Long outcomeId) {
		this.outcomeId = outcomeId;
	}
	public Double getOdds() {
		return odds;
	}
	public void setOdds(Double odds) {
		this.odds = odds;
	}
	

}
