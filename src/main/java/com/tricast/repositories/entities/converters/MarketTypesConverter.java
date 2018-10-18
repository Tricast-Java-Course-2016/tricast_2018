package com.tricast.repositories.entities.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.tricast.repositories.entities.MarketTypes;

import static com.tricast.repositories.entities.MarketTypes.*;

@Converter
public class MarketTypesConverter implements AttributeConverter<MarketTypes, String> {

    @Override
    public String convertToDatabaseColumn(MarketTypes attribute) {
        switch (attribute) {
            case WDW:
                return "WDW";
            case CorrectScore:
                return "Correct score";
            case OverUnderGoalsOneAndAHalf:
                return "Over/Under Goals 1.5";
            case OverUnderGoalsTwoAndAHalf:
            	return "Over/Under Goals 2.5";
            case OverUnderCornersEightAndAHalf:
            	return "Over/Under Corners 8.5";
            case HalfTimeFullTime:
            	return "Half time / Full time";
            case Outright:
            	return "Outright";
            case HeadToHead:
            	return "Head to head";	
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public MarketTypes convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "WDW":
                return WDW;
            case "Correct score":
                return CorrectScore;
            case "Over/Under Goals 1.5":
                return OverUnderGoalsOneAndAHalf;
            case "Over/Under Goals 2.5":
            	return OverUnderGoalsTwoAndAHalf;
            case "Over/Under Corners 8.5":
            	return OverUnderCornersEightAndAHalf;
            case "Half time / Full time":
            	return HalfTimeFullTime;
            case "Outright":
            	return Outright;
            case "Head to head":
            	return HeadToHead;		
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}