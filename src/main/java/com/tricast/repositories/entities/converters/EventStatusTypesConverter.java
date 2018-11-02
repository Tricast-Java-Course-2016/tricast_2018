package com.tricast.repositories.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.tricast.repositories.entities.EventStatusTypes;

import static com.tricast.repositories.entities.EventStatusTypes.*;

@Converter
public class EventStatusTypesConverter implements AttributeConverter<EventStatusTypes, String> { 

	@Override
	public String convertToDatabaseColumn(EventStatusTypes attribute) {
		switch (attribute) {
		case OPEN:
			return "OPEN";
		case CLOSED:
			return "CLOSED";
		default:
			throw new IllegalArgumentException("Unknown" + attribute);
		}
	}

	@Override
	public EventStatusTypes convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "OPEN":
			return OPEN;
		case "CLOSED":
			return CLOSED;
		default:
			throw new IllegalArgumentException("Unknown" + dbData);
		}
	}
}
