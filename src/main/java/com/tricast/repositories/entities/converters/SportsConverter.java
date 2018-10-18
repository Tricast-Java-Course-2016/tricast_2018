package com.tricast.repositories.entities.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.tricast.repositories.entities.Sports;

import static com.tricast.repositories.entities.Sports.*;

@Converter
public class SportsConverter implements AttributeConverter<Sports, String> {

    @Override
    public String convertToDatabaseColumn(Sports attribute) {
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
    public Sports convertToEntityAttribute(String dbData) {
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