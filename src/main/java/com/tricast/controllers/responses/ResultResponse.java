package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.PeriodTypes;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypes;

public class ResultResponse implements Serializable{
	private static final long serialVersionUID = 2646725308970295770L;

	private long id;
    
    private Competitor competitorId;
    
    private PeriodType periodTypeId;
    private ResultType resultTypeId;
    private int result;
    
    private List<PeriodType> periodTypes = new ArrayList<PeriodType>();
    private List<Competitor> competitors = new ArrayList<Competitor>();
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Competitor getCompetitorId() {
		return competitorId;
	}
	public void setCompetitorId(Competitor competitorId) {
		this.competitorId = competitorId;
	}
	public PeriodType getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(PeriodType periodTypeId) {
		this.periodTypeId = periodTypeId;
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
	public List<PeriodType> getPeriodTypes() {
		return periodTypes;
	}
	public void setPeriodTypes(List<PeriodType> periodTypes) {
		this.periodTypes = periodTypes;
	}
	public List<Competitor> getCompetitors() {
		return competitors;
	}
	public void setCompetitors(List<Competitor> competitors) {
		this.competitors = competitors;
	}
}
