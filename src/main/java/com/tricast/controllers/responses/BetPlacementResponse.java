package com.tricast.controllers.responses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BetPlacementResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3201619938882083659L;
	
	private List <BetResponse> listOfBetResponses;
	private BigDecimal sumStake;
	private BigDecimal sumPotentialWin;	

	public BigDecimal getSumStake() {
		return sumStake;
	}

	public void setSumStake(BigDecimal sumStake) {
		this.sumStake = sumStake;
	}

	public BigDecimal getSumPotentialWin() {
		return sumPotentialWin;
	}

	public void setSumPotentialWin(BigDecimal sumPotentialWin) {
		this.sumPotentialWin = sumPotentialWin;
	}

	public List<BetResponse> getListOfBetResponses() {
		return listOfBetResponses;
	}

	public void setListOfBetResponses(List<BetResponse> listOfBetResponses) {
		this.listOfBetResponses = listOfBetResponses;
	}
	
}
