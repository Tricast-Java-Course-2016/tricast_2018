package com.tricast.controllers.requests;

import java.io.Serializable;

public class ResultRequest implements Serializable {

	private static final long serialVersionUID = -6817451528421899276L;

    private long resultTypeId;
    private int result;
    private long periodTypeId;
    private long competitorId;

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

    public long getPeriodTypeId() {
        return periodTypeId;
    }

    public void setPeriodTypeId(long periodTypeId) {
        this.periodTypeId = periodTypeId;
    }

    public long getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(long competitorId) {
        this.competitorId = competitorId;
    }


}
