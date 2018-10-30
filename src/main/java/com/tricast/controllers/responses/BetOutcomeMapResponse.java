package com.tricast.controllers.responses;

import java.io.Serializable;

public class BetOutcomeMapResponse implements Serializable {

    // Remove completely
	/**
	 *
	 */
	private static final long serialVersionUID = 6569449988528471674L;

	private Long betId;
	private Long outcomeId;
	private double odds;

	public Long getBetId() {
		return betId;
	}
	public Long getOutcomeId() {
		return outcomeId;
	}
	public double getOdds() {
		return odds;
	}

}
