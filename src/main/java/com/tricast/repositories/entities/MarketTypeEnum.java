package com.tricast.repositories.entities;

import java.util.Arrays;
import java.util.List;

public enum MarketTypeEnum {
    WDW(1, "WDW", ResultTypeEnum.GOALS),
    CorrectScore(2, "Correct Score", ResultTypeEnum.GOALS),
    OverUnderGoalsOneAndAHalf(3, "Over/Under Goals 1.5", ResultTypeEnum.GOALS),
    OverUnderGoalsTwoAndAHalf(4, "Over/Under Goals 2.5", ResultTypeEnum.GOALS),
    OverUnderCornersEightAndAHalf(5, "Over/Under Corners 8.5", ResultTypeEnum.CORNERS),
    HalfTimeFullTime(6, "Half time/Full time", ResultTypeEnum.GOALS),
    Outright(7, "Outright", ResultTypeEnum.POSITIONS),
    HeadToHead(8, "Head to Head", ResultTypeEnum.POSITIONS);

	private final int id;
    private final String description;
    private final ResultTypeEnum resultType;

    private MarketTypeEnum(int id, String value, ResultTypeEnum resultType) {
        this.id = id;
    	this.description = value;
    	this.resultType = resultType;
    }

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public ResultTypeEnum getResultType() {
		return resultType;
	}
}
