package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.PeriodType;

public interface PeriodTypeRepository extends CrudRepository<PeriodType, Long> {

	@Override
    List<PeriodType> findAll();

    PeriodType findById(Long id);

}
