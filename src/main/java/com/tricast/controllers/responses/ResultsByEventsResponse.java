package com.tricast.controllers.responses;

import java.util.ArrayList;
import java.util.List;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.ResultType;

public class ResultsByEventsResponse {
	
	private List<ResultResponse> resultResponseList = new ArrayList<ResultResponse>();
	private List<PeriodType> periodResponseList = new ArrayList<PeriodType>();
	private List<ResultType> resultTypeResponseList = new ArrayList<ResultType>(); 
	private List<Competitor> competitorList = new ArrayList<Competitor>();
	
	public List<ResultResponse> getResultResponseList() {
		return resultResponseList;
	}
	public void setResultResponseList(List<ResultResponse> resultResponseList) {
		this.resultResponseList = resultResponseList;
	}
	public List<PeriodType> getPeriodResponseList() {
		return periodResponseList;
	}
	public void setPeriodResponseList(List<PeriodType> periodResponseList) {
		this.periodResponseList = periodResponseList;
	}
	public List<ResultType> getResultTypeResponseList() {
		return resultTypeResponseList;
	}
	public void setResultTypeResponseList(List<ResultType> resultTypeResponseList) {
		this.resultTypeResponseList = resultTypeResponseList;
	}
	public List<Competitor> getCompetitorList() {
		return competitorList;
	}
	public void setCompetitorList(List<Competitor> competitorList) {
		this.competitorList = competitorList;
	}
}
