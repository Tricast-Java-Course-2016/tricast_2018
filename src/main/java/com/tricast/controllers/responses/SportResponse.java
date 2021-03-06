package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

import com.tricast.repositories.entities.MarketTypeEnum;
import com.tricast.repositories.entities.PeriodTypeEnum;

public class SportResponse implements Serializable {

	private static final long serialVersionUID = -3901768642115466847L;
	
	private Long id;
	private String description;
	private List<MarketTypeResponse> marketTypes;
	private List<PeriodTypeResponse> periodTypes;
	
	public List<MarketTypeResponse> getMarketTypes() {
		return marketTypes;
	}

	public void setMarketTypes(List<MarketTypeResponse> marketTypes) {
		this.marketTypes = marketTypes;
	}

	public List<PeriodTypeResponse> getPeriodTypes() {
		return periodTypes;
	}

	public void setPeriodTypes(List<PeriodTypeResponse> periodTypes) {
		this.periodTypes = periodTypes;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
