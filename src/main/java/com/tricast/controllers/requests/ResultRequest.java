package com.tricast.controllers.requests;

import java.io.Serializable;

public class ResultRequest implements Serializable {

	private static final long serialVersionUID = -6817451528421899276L;
	
	private Long resultTypeId;
    private int result;
    private Long periodTypeId;
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
