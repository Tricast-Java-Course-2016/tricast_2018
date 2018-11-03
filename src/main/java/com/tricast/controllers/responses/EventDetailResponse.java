package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tricast.repositories.entities.PeriodMarkets;

public class EventDetailResponse  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6776837782506521727L;
	
	
	private String eventDescription;
	private Calendar eventStartTime;
	private List <PeriodMarkets> periodMarketsList= new ArrayList <PeriodMarkets>();
	
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Calendar getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(Calendar eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public List<PeriodMarkets> getPeriodMarketsList() {
		return periodMarketsList;
	}
	public void setPeriodMarketsList(List<PeriodMarkets> periodMarketsList) {
		this.periodMarketsList = periodMarketsList;
	}
	
}