package com.tricast.repositories.entities;

public enum PeriodTypeEnum {
    NINETYMINUTES(1, "Ninety Minutes"), 
    FIRSTHALF(2, "First Half"), 
    SECONDHALF(3, "Second Half"), 
    RACE(4, "Race");

	private final int id;
    private final String description;

    
    private PeriodTypeEnum(int id, String value) {
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
