package com.tricast.controllers.responses;

import java.io.Serializable;

public class BetResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 7691801419776027759L;

    // Missing, bet status, Outcome, Event, Market descriptions, BetType, Stake, Odds, PotentialWin
	private long bettypeId;
	private long accountId;
	
	private String betStatus;
	private String outcome;
	private String event;
	private String marketDescription;
	private String betType;
	private long stake;
	private long odds;
	private long potentialWin;
	
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
	public String getBetStatus() {
		return betStatus;
	}
	public void setBetStatus(String betStatus) {
		this.betStatus = betStatus;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getMarketDescription() {
		return marketDescription;
	}
	public void setMarketDescription(String marketDescription) {
		this.marketDescription = marketDescription;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public long getStake() {
		return stake;
	}
	public void setStake(long stake) {
		this.stake = stake;
	}
	public long getOdds() {
		return odds;
	}
	public void setOdds(long odds) {
		this.odds = odds;
	}
	public long getPotentialWin() {
		return potentialWin;
	}
	public void setPotentialWin(long potentialWin) {
		this.potentialWin = potentialWin;
	}
	
	
	
}
