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
    private long periodTypeId;
    private long resultTypeId;
    private int result;
    
    private long comeptitorId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(long periodTypeId) {
		this.periodTypeId = periodTypeId;
	}

	public long getResultTypeId() {
		return resultTypeId;
	}

	public void setResultTypeId(long resultTypeId) {
		this.resultTypeId = resultTypeId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public long getComeptitorId() {
		return comeptitorId;
	}

	public void setComeptitorId(long comeptitorId) {
		this.comeptitorId = comeptitorId;
	}
}
