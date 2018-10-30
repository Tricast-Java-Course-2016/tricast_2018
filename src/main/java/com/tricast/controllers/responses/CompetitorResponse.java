package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

public class CompetitorResponse implements Serializable {

	private static final long serialVersionUID = 6809698961726889351L;

	private Long id;
	private String description;
    // List of Leagues would be better
	private List<Long> leagueIds;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
