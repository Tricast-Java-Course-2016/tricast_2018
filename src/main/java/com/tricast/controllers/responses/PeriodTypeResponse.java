package com.tricast.controllers.responses;

import java.io.Serializable;

public class PeriodTypeResponse implements Serializable{

	private static final long serialVersionUID = 6366007905616529333L;

	private int id;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
