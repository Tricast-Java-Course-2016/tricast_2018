package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypeEnum;

public class ResultRequest implements Serializable {

	private static final long serialVersionUID = -6817451528421899276L;

    private int resultTypeId;
    private int result;
    private int periodTypeId;

    private long competitorId;

	public int getResultTypeId() {
		return resultTypeId;
	}

	public void setResultTypeId(int resultTypeId) {
		this.resultTypeId = resultTypeId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(int periodTypeId) {
		this.periodTypeId = periodTypeId;
	}

	public long getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(long competitorId) {
		this.competitorId = competitorId;
	}
}
