package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

import com.tricast.repositories.entities.League;

public class CompetitorResponse implements Serializable {
	
	private static final long serialVersionUID = 6809698961726889351L;
	
	private Long id;
	private String description; 
	private List<League> leagues;
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
	public List<League> getLeagues() {
		return leagues;
	}
	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}
	
	
}
