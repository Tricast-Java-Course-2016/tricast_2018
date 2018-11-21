package com.tricast.repositories.entities;

public enum ResultTypeEnum{
	GOALS(1, "Goals"),
	CORNERS(2, "Corners"),
	POSITIONS(3, "Position");
	
	private final int id;
	private final String description;
	
	private ResultTypeEnum(int id, String value) {
		this.id = id;
		this.description = value;
	}

	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
}
