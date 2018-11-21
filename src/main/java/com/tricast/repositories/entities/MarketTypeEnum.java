package com.tricast.repositories.entities;

import java.util.Arrays;
import java.util.List;

public enum MarketTypeEnum {
    WDW("WDW", Arrays.asList(SportEnum.FOOTBALL)),
    CorrectScore("Correct Score", Arrays.asList(SportEnum.FOOTBALL)),
    OverUnderGoalsOneAndAHalf("Over/Under Goals 1.5", Arrays.asList(SportEnum.FOOTBALL)),
    OverUnderGoalsTwoAndAHalf("Over/Under Goals 2.5", Arrays.asList(SportEnum.FOOTBALL)),
    OverUnderCornersEightAndAHalf("Over/Under Corners 8.5", Arrays.asList(SportEnum.FOOTBALL)),
    HalfTimeFullTime("Half time/Full time", Arrays.asList(SportEnum.FOOTBALL)),
    Outright("Outright", Arrays.asList(SportEnum.HORSERACING)),
    HeadToHead("Head to Head", Arrays.asList(SportEnum.HORSERACING));

    private final String description;
    private final List<SportEnum> sports;

    private MarketTypeEnum(String value, List<SportEnum> sports) {
        this.description = value;
        this.sports = sports;
    }

    public String getDescription() {
        return description;
    }

    public List<SportEnum> getSports() {
        return sports;
    }
}
