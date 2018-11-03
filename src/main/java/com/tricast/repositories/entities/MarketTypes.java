package com.tricast.repositories.entities;

public enum MarketTypes {
	WDW("WDW"),CorrectScore("Correct Score"),OverUnderGoalsOneAndAHalf("Over/Under Goals 1.5"),OverUnderGoalsTwoAndAHalf("Over/Under Goals 2.5"),
	OverUnderCornersEightAndAHalf("Over/Under Corners 8.5"),HalfTimeFullTime("Half time/Full time"),Outright("Outright"),HeadToHead("Head to Head");
	
	
	 private final String description;

	    private MarketTypes(String value) {
	        description = value;
	    }

	    public String getValue() {
	    	return description;
	    }
}
