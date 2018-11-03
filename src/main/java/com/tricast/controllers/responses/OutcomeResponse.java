package com.tricast.controllers.responses;

import java.io.Serializable;

public class OutcomeResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7158668470243241029L;
	
	private String marketDescription;
	private String outcomeDescription;
	private double odds;
	
	public String getMarketDescription() {
		return marketDescription;
	}
	
	public void setMarketDescription(String marketDescription) {
		this.marketDescription = marketDescription;
	}
	public String getOutcomeDescription() {
		return outcomeDescription;
	}
	public void setOutcomeDescription(String outcomeDescription) {
		this.outcomeDescription = outcomeDescription;
	}
	public double getOdds() {
		return odds;
	}
	public void setOdds(double odds) {
		this.odds = odds;
	}
	
	

}
