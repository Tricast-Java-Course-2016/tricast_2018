package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

public class LeagueResponse implements Serializable {

	private static final long serialVersionUID = -7016467317642235818L;

    // Use primitive
	private long id;
	private String description;
    // Use primitive
	private long sportId;
    // List of Competitors instead
	private List<Long> competitorIds;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public List<Long> getCompetitorIds() {
		return competitorIds;
	}
	public void setCompetitorIds(List<Long> competitorIds) {
		this.competitorIds = competitorIds;
	}
}
