package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;

public class LeagueRequest implements Serializable {

	private static final long serialVersionUID = -3370578224985028407L;

	private String description;
	private List<Long> competitorIds;
    // Use primitive
	private Long sportId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getCompetitorIds() {
		return competitorIds;
	}

	public void setCompetitorIds(List<Long> competitorIds) {
		this.competitorIds = competitorIds;
	}

	public Long getSportId() {
		return sportId;
	}

	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}
}
