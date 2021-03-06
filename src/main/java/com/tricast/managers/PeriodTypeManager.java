package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.PeriodTypeEnum;

public interface PeriodTypeManager {

    List<PeriodType> findAll();

    PeriodType findById(int id);

    PeriodType create(PeriodType periodtype);

    PeriodType update(PeriodType periodtype);

    void deleteById(Long id);
    
    List<PeriodTypeEnum> findByEventId(long eventId);
}
