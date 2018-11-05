package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

public class ResultSaveResponse implements Serializable {
	
	private static final long serialVersionUID = -2135419002780681228L;
	
	private long id;
	private long eventId;
	private List<ResultResponse> responseToSave;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public List<ResultResponse> getResponseToSave() {
		return responseToSave;
	}
	public void setResponseToSave(List<ResultResponse> responseToSave) {
		this.responseToSave = responseToSave;
	}

}
