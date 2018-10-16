package com.tricast.repositories.entities;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import static com.tricast.repositories.entities.Sport.*;

@Converter
public class SportsConverter implements AttributeConverter<Sport, String> {

    @Override
    public String convertToDatabaseColumn(Sport attribute) {
        switch (attribute) {
            case FOOTBALL:
                return "Football";
            case HORSERACING:
                return "Horse Racing";
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public Sport convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "Football":
                return FOOTBALL;
            case "Horse Racing":
                return HORSERACING;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}