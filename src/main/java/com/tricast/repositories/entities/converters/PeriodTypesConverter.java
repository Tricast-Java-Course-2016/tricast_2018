package com.tricast.repositories.entities.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.tricast.repositories.entities.PeriodTypeEnum;

import static com.tricast.repositories.entities.PeriodTypeEnum.*;

@Converter
public class PeriodTypesConverter implements AttributeConverter<PeriodTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(PeriodTypeEnum attribute) {
        switch (attribute) {
            case NINETYMINUTES:
                return "90 minutes";
            case FIRSTHALF:
                return "First half";
            case SECONDHALF:
                return "Second Half";
            case RACE:
            	return "Rac";
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public PeriodTypeEnum convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "90 minutes":
                return NINETYMINUTES;
            case "First half":
                return FIRSTHALF;
            case "Second half":
                return SECONDHALF;
            case "Rac":
            	return RACE;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}