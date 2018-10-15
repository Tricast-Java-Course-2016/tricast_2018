package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.PeriodTypes;

public interface PeriodTypesRepository extends CrudRepository<PeriodTypes, Long> {

	@Override
    List<PeriodTypes> findAll();

    PeriodTypes findById(Long id);

}
