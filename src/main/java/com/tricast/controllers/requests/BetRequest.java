package com.tricast.controllers.requests;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class BetRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 750396854994179350L;

    
	private long bettypeId;
	private long accountId;
	private BigDecimal betStake;
	private Map<Long, Double> outcomeOdds;
	
	public long getBettypeId() {
		return bettypeId;
	}
	public void setBettypeId(long bettypeId) {
		this.bettypeId = bettypeId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getBetStake() {
		return betStake;
	}
	public void setBetStake(BigDecimal betStake) {
		this.betStake = betStake;
	}
	public Map<Long, Double> getOutcomeOdds() {
		return outcomeOdds;
	}
	public void setOutcomeOdds(Map<Long, Double> outcomeOdds) {
		this.outcomeOdds = outcomeOdds;
	}

}
