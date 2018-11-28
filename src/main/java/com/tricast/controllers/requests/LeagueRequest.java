package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;

public class LeagueRequest implements Serializable {

	private static final long serialVersionUID = -3370578224985028407L;

	private String description;
	private long sportId;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getSportId() {
		return sportId;
	}
	public void setSportId(long sportId) {
		this.sportId = sportId;
	}

	
}
