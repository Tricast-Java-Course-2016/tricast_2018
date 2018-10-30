package com.tricast.controllers.responses;

import java.io.Serializable;

public class BetResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 7691801419776027759L;

    // Missing, bet status, Outcome, Event, Market descriptions, prices, BetType, Stake, Odds, PotentialWin
	private Long bettypeId;
	private Long accountId;

	public Long getBettypeId() {
		return bettypeId;
	}
	public Long getAccountId() {
		return accountId;
	}

}
