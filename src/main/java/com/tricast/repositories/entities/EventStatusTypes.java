package com.tricast.repositories.entities;

public enum EventStatusTypes {
	OPEN("OPEN"), CLOSED("CLOSED");
	

	 private final String description;

	    private EventStatusTypes(String value) {
	        description = value;
	    }

	    public String getValue() {
	    	return description;
	    }
}
