package com.tricast.controllers.requests;

import java.io.Serializable;

public class ResultRequest implements Serializable {

	private static final long serialVersionUID = -6817451528421899276L;

    // There should be a list of these here

    // Primitive
	private Long resultTypeId;
    // Primitive
    private int result;
    // Primitive
    private Long periodTypeId;
    // Primitive
    private Long eventCompetitorMapId;

    public ResultRequest(Long resultTypeId, int result, Long periodTypeId, Long eventCompetitorMapId) {
        this.result = result;
        this.periodTypeId = periodTypeId;
        this.eventCompetitorMapId = eventCompetitorMapId;
    }

    public Long getResultTypeId() {
    	return resultTypeId;
    }

    public int getResult() {
        return result;
    }

    public Long getPeriodTypeId() {
        return periodTypeId;
    }

    public Long getEventCompetitorMapId() {
        return eventCompetitorMapId;
    }

}
