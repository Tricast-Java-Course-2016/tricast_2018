package com.tricast.repositories.entities;

public enum ResultTypeEnum{
	GOALS("Goals"), CORNERS("Corners"), POSITIONS("Position");
	
	private final String description;
	
	private ResultTypeEnum(String value) {
		description = value;
	}

	public String getDescription() {
		return description;
	}
}
