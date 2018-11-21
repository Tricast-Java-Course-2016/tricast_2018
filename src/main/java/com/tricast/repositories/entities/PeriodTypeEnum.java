package com.tricast.repositories.entities;

public enum PeriodTypeEnum {
    NINETYMINUTES("Ninety Minutes"), FIRSTHALF("First Half"), SECONDHALF("Second Half"), RACE("Race");

    private final String description;

    private PeriodTypeEnum(String value) {
        description = value;
    }

    public String getValue() {
        return description;
    }
}
