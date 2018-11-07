package com.tricast.controllers.responses;

import java.io.Serializable;

public class MarketResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 4720117817228280965L;


	private String marketDescription;
	private String marketTypeDescription;
	private long marketId;
	private String status;


	public String getMarketDescription() {
		return marketDescription;
	}
	public void setMarketDescription(String marketDescription) {
		this.marketDescription = marketDescription;
	}
	public String getMarketTypeDescription() {
		return marketTypeDescription;
	}
	public void setMarketTypeDescription(String marketTypeDescription) {
		this.marketTypeDescription = marketTypeDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getMarketId() {
		return marketId;
	}
	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}
}
