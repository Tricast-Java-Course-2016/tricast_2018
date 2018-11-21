package com.tricast.repositories.entities.converters;

import static com.tricast.repositories.entities.ResultTypeEnum.*;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.tricast.repositories.entities.ResultTypeEnum;

@Converter
public class ResultTypesConverter implements AttributeConverter<ResultTypeEnum, String> {

	@Override
	public String convertToDatabaseColumn(ResultTypeEnum attribute) {
        switch (attribute) {
        case GOALS:
            return "Goals";
        case CORNERS:
            return "Corners";
        case POSITIONS:
            return "Positions";
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public ResultTypeEnum convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "Goals":
            return GOALS;
        case "Corners":
            return CORNERS;
        case "Positions":
            return POSITIONS;
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
        }
	}

}
