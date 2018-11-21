package com.tricast.managers.mappers;

import com.tricast.controllers.responses.PeriodTypeResponse;
import com.tricast.repositories.entities.PeriodType;

public class PeriodTypeResponseMapper {

	public static PeriodTypeResponse build(PeriodType periodType) {

		PeriodTypeResponse periodTypeResponse = new PeriodTypeResponse();

        periodTypeResponse.setDescription(periodType.getDescription());

		return periodTypeResponse;
    }

}