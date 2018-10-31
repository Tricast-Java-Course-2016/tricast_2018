package com.tricast.controllers.requests;

import java.io.Serializable;

public class BetRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 750396854994179350L;

    // Missing OutcomeId+Odds map, please add them.
    // Missing BetStake/TotalStake
	private Long bettypeId;
	private Long accountId;

	public Long getBettypeId() {
		return bettypeId;
	}
	public void setBettypeId(Long bettypeId) {
		this.bettypeId = bettypeId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}



}
