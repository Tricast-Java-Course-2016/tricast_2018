package com.tricast.repositories.entities;

public enum PeriodTypes {
	NINETYMINUTES("Ninety Minutes"),FIRSTHALF("First Half"),SECONDHALF("Second Half"),RACE("Race");
	
	 private final String description;

	    private PeriodTypes(String value) {
	        description = value;
	    }

	    public String getValue() {
	    	return description;
	    }
}
