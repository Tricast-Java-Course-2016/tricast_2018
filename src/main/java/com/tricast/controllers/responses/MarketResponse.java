package com.tricast.controllers.responses;

import java.io.Serializable;

public class MarketResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 4720117817228280965L;

    // AKOS duplicált
    // rajt van már az EventDetailResponse
    // és inkább oda is tartozik
	private String eventDescription;
	private String marketDescription;
	private String marketTypeDescription;

    // AKOS
    // ez se kell mivel a PeriodMarkets összeköti már a Marketeket a perioddal logikalag
    // (vagy az nem kell de a kettő együtt sztem felesleges)
    private String periodTypeDescription;
	private String status;

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
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
	public String getPeriodTypeDescription() {
		return periodTypeDescription;
	}
	public void setPeriodTypeDescription(String periodTypeDescription) {
		this.periodTypeDescription = periodTypeDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
