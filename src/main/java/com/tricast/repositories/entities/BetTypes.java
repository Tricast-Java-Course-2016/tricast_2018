package com.tricast.repositories.entities;

public enum BetTypes {
	Single("Single"),Double("Double"),Treble("Treble");
	
	 private final String description;

	    private BetTypes(String value) {
	        description = value;
	    }

	    public String getValue() {
	    	return description;
	    }
}
