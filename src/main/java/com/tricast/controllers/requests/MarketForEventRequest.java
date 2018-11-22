package com.tricast.controllers.requests;

import java.io.Serializable;

public class MarketForEventRequest implements Serializable  {

	private static final long serialVersionUID = 4617316053970844761L;

	private long marketTypeId;
	private int periodTypeId;
	
	public long getMarketTypeId() {
		return marketTypeId;
	}
	public void setMarketTypeId(long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}
	public int getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(int periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
}
