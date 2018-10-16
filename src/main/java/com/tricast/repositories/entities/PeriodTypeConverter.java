package com.tricast.repositories.entities;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import static com.tricast.repositories.entities.PeriodType.*;

@Converter
public class PeriodTypeConverter implements AttributeConverter<PeriodType, String> {

    @Override
    public String convertToDatabaseColumn(PeriodType attribute) {
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
    public PeriodType convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "90 Minutes":
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