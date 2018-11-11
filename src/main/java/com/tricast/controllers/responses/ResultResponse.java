package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.ResultType;

public class ResultResponse implements Serializable{
	private static final long serialVersionUID = 2646725308970295770L;

	private long id;
    
    private ResultType resultTypeId;
    private int result;
    private PeriodType periodTypeId;
    
    //private List<Event> events = new ArrayList<Event>();
    //private List<Competitor> competitors = new ArrayList<Competitor>();
    
    private long eventCompetitorMapId;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public ResultType getResultTypeId() {
		return resultTypeId;
	}
	public void setResultTypeId(ResultType resultTypeId) {
		this.resultTypeId = resultTypeId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public PeriodType getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(PeriodType periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
	public long getEventCompetitorMapId() {
		return eventCompetitorMapId;
	}
	public void setEventCompetitorMapId(long eventCompetitorMapId) {
		this.eventCompetitorMapId = eventCompetitorMapId;
	}
    
}
