package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypes;

public class ResultRequest implements Serializable {

	private static final long serialVersionUID = -6817451528421899276L;

    private ResultType resultTypeId;
    private int result;
    private PeriodType periodTypeId;
    
    private long eventCompetitorMapId;
    
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
