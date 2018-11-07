package com.tricast.controllers.responses;

import java.io.Serializable;

public class OutcomeResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 7158668470243241029L;

	private String outcomeDescription;
	private long outcomeId;
	private double odds;

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

	public long getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(long outcomeId) {
		this.outcomeId = outcomeId;
	}

}
