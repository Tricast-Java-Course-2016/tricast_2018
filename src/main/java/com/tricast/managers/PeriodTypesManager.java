package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.PeriodTypes;

public interface PeriodTypesManager {

    List<PeriodTypes> findAll();

    PeriodTypes findById(Long id);

    PeriodTypes create(PeriodTypes periodtype);

    PeriodTypes update(PeriodTypes periodtype);

    void deleteById(Long id);

}
