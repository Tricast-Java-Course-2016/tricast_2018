package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

public class ResultSaveResponse implements Serializable {
	
	private static final long serialVersionUID = -2135419002780681228L;
	
	private long eventCompetitorMapId;
	private ResultResponse responseToSave;
	
	public long getEventCompetitorMapId() {
		return eventCompetitorMapId;
	}
	public void setEventCompetitorMapId(long eventCompetitorMapId) {
		this.eventCompetitorMapId = eventCompetitorMapId;
	}
	public ResultResponse getResponseToSave() {
		return responseToSave;
	}
	public void setResponseToSave(ResultResponse responseToSave) {
		this.responseToSave = responseToSave;
	}
}
