package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

public class LeagueResponse implements Serializable {
	
	private static final long serialVersionUID = -7016467317642235818L;
	
	private Long id;
	private String description;
	private Long sportId;
	private List<Long> competitorIds;
	
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

	public Long getSportId() {
		return sportId;
	}

	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}

	public List<Long> getCompetitorIds() {
		return competitorIds;
	}

	public void setCompetitorIds(List<Long> competitorIds) {
		this.competitorIds = competitorIds;
	}
	
}
