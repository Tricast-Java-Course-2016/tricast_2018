package com.tricast.controllers.responses;

import java.util.ArrayList;
import java.util.List;

import com.tricast.repositories.entities.Competitor;

public class ResultsByEventsResponse {
	
	private List<ResultResponse> resultResponseList = new ArrayList<ResultResponse>();
	private List<PeriodResponse> periodResponseList = new ArrayList<PeriodResponse>();
	
	public List<ResultResponse> getResultResponseList() {
		return resultResponseList;
	}
	public void setResultResponseList(List<ResultResponse> resultResponseList) {
		this.resultResponseList = resultResponseList;
	}
	public List<PeriodResponse> getPeriodResponseList() {
		return periodResponseList;
	}
	public void setPeriodResponseList(List<PeriodResponse> periodResponseList) {
		this.periodResponseList = periodResponseList;
	}

}
