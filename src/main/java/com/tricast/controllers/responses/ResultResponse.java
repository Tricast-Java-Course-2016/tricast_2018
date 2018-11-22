package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.PeriodTypeEnum;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypeEnum;

public class ResultResponse implements Serializable{
	private static final long serialVersionUID = 2646725308970295770L;

	private long id;
    private int periodTypeId;
    private int resultTypeId;
    private int result;
    
    private long comeptitorId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(int periodTypeId) {
		this.periodTypeId = periodTypeId;
	}

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

	public long getComeptitorId() {
		return comeptitorId;
	}

	public void setComeptitorId(long comeptitorId) {
		this.comeptitorId = comeptitorId;
	}
}
