package com.tricast.controllers.responses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BetResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 7691801419776027759L;

    private long bettypeId;
	private long accountId;
	
	private List <String> betStatus;
	private List <String> outcome;
	private List <String> event;
	private List <String> marketDescription;
	private String betType;
	private BigDecimal stake;
	private List <Double> odds;
	private double sumOdds;
	private BigDecimal potentialWin;
	
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
	public List<String> getBetStatus() {
		return betStatus;
	}
	public void setBetStatus(List<String> betStatus) {
		this.betStatus = betStatus;
	}
	public List<String> getOutcome() {
		return outcome;
	}
	public void setOutcome(List<String> outcome) {
		this.outcome = outcome;
	}
	public List<String> getEvent() {
		return event;
	}
	public void setEvent(List<String> event) {
		this.event = event;
	}
	public List<String> getMarketDescription() {
		return marketDescription;
	}
	public void setMarketDescription(List<String> marketDescription) {
		this.marketDescription = marketDescription;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public BigDecimal getStake() {
		return stake;
	}
	public void setStake(BigDecimal stake) {
		this.stake = stake;
	}
	public List<Double> getOdds() {
		return odds;
	}
	public void setOdds(List<Double> odds) {
		this.odds = odds;
	}
	public double getSumOdds() {
		return sumOdds;
	}
	public void setSumOdds(double sumOdds) {
		this.sumOdds = sumOdds;
	}
	public BigDecimal getPotentialWin() {
		return potentialWin;
	}
	public void setPotentialWin(BigDecimal potentialWin) {
		this.potentialWin = potentialWin;
	}
	

}
