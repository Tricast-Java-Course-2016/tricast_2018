package com.tricast.repositories.entities;

import java.util.Arrays;
import java.util.List;

public enum MarketTypeEnum {
    WDW(1, "WDW", PeriodTypeEnum.NINETYMINUTES, ResultTypeEnum.GOALS),
    CorrectScore(2, "Correct Score", PeriodTypeEnum.NINETYMINUTES, ResultTypeEnum.GOALS),
    OverUnderGoalsOneAndAHalf(3, "Over/Under Goals 1.5", PeriodTypeEnum.NINETYMINUTES, ResultTypeEnum.GOALS),
    OverUnderGoalsTwoAndAHalf(4, "Over/Under Goals 2.5", PeriodTypeEnum.NINETYMINUTES, ResultTypeEnum.GOALS),
    OverUnderCornersEightAndAHalf(5, "Over/Under Corners 8.5", PeriodTypeEnum.NINETYMINUTES, ResultTypeEnum.CORNERS),
    HalfTimeFullTime(6, "Half time/Full time", PeriodTypeEnum.NINETYMINUTES, ResultTypeEnum.GOALS),
    Outright(7, "Outright", PeriodTypeEnum.RACE, ResultTypeEnum.POSITIONS),
    HeadToHead(8, "Head to Head", PeriodTypeEnum.RACE, ResultTypeEnum.POSITIONS);

	private final int id;
    private final String description;
    private final PeriodTypeEnum periodType;
    private final ResultTypeEnum resultType;

    private MarketTypeEnum(int id, String value, PeriodTypeEnum periodType, ResultTypeEnum resultType) {
        this.id = id;
    	this.description = value;
    	this.periodType = periodType;
    	this.resultType = resultType;
    }

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public PeriodTypeEnum getPeriodType() {
		return periodType;
	}

	public ResultTypeEnum getResultType() {
		return resultType;
	}
}
