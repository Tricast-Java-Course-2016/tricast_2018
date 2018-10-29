package com.tricast.controllers.responses;

import java.io.Serializable;

public class SportResponse implements Serializable {

	private static final long serialVersionUID = -3901768642115466847L;
	
	private Long id;
	private String description;
	
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
}
