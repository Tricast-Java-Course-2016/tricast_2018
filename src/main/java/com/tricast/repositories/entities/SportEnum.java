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
                    PeriodTypeEnum.SECONDHALF)),
    HORSERACING(Arrays.asList(
                    MarketTypeEnum.HeadToHead, 
                    MarketTypeEnum.Outright), 
           Arrays.asList(
                    PeriodTypeEnum.RACE));

    private final List<MarketTypeEnum> marketTypes;
    private final List<PeriodTypeEnum> periodTpes;

    private SportEnum(List<MarketTypeEnum> marketTypes, List<PeriodTypeEnum> periodTpes) {
        this.marketTypes = marketTypes;
        this.periodTpes = periodTpes;
    }

    public List<MarketTypeEnum> getMarketTypes() {
        return marketTypes;
    }

    public List<PeriodTypeEnum> getPeriodTpes() {
        return periodTpes;
    }
}
