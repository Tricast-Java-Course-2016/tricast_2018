package com.tricast.repositories.entities;

import java.util.Arrays;
import java.util.List;

public enum SportEnum {
    FOOTBALL(Arrays.asList(
                    MarketTypeEnum.WDW,
                    MarketTypeEnum.CorrectScore,
                    MarketTypeEnum.HalfTimeFullTime,
                    MarketTypeEnum.OverUnderCornersEightAndAHalf,
                    MarketTypeEnum.OverUnderGoalsOneAndAHalf,
                    MarketTypeEnum.OverUnderGoalsTwoAndAHalf),
            Arrays.asList(
                    PeriodTypeEnum.NINETYMINUTES,
                    PeriodTypeEnum.FIRSTHALF,
                    PeriodTypeEnum.SECONDHALF),
            Arrays.asList(
            		ResultTypeEnum.GOALS,
            		ResultTypeEnum.CORNERS)),
    HORSERACING(Arrays.asList(
                    MarketTypeEnum.HeadToHead, 
                    MarketTypeEnum.Outright), 
           Arrays.asList(
                    PeriodTypeEnum.RACE),
           Arrays.asList(
        		   	ResultTypeEnum.POSITIONS));

    private final List<MarketTypeEnum> marketTypes;
    private final List<PeriodTypeEnum> periodTypes;
    private final List<ResultTypeEnum> resultTypes;

    private SportEnum(List<MarketTypeEnum> marketTypes, List<PeriodTypeEnum> periodTypes, List<ResultTypeEnum> resultTypes) {
        this.marketTypes = marketTypes;
        this.periodTypes = periodTypes;
        this.resultTypes = resultTypes;
        marketTypes = marketTypes;
    }

    public List<MarketTypeEnum> getMarketTypes() {
        return this.marketTypes;
    }

    public List<PeriodTypeEnum> getPeriodTypes() {
        return this.periodTypes;
    }

	public List<ResultTypeEnum> getResultTypes() {
		return resultTypes;
	}
}
