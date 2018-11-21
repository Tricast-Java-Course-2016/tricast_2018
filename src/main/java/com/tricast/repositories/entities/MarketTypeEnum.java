package com.tricast.repositories.entities;

public enum MarketTypeEnum {
    WDW(1, "WDW"),
    CorrectScore(2, "Correct Score"),
    OverUnderGoalsOneAndAHalf(3, "Over/Under Goals 1.5"),
    OverUnderGoalsTwoAndAHalf(4, "Over/Under Goals 2.5"),
    OverUnderCornersEightAndAHalf(5, "Over/Under Corners 8.5"),
    HalfTimeFullTime(6, "Half time/Full time"),
    Outright(7, "Outright"),
    HeadToHead(8, "Head to Head");

	private final int id;
    private final String description;

    private MarketTypeEnum(int id, String value) {
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
