package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;

public class CompetitorRequest implements Serializable {

	private static final long serialVersionUID = 11123848540838688L;

	private String description;
	private List<Long> leagueIds;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Long> getLeagueIds() {
		return leagueIds;
	}
	
	public void setLeagueIds(List<Long> leagueIds) {
		this.leagueIds = leagueIds;
	}
	
	
}
