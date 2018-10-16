package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Outcomes;

public interface OutcomesRepository extends CrudRepository<Outcomes, Long> {

	@Override
    List<Outcomes> findAll();

    Outcomes findById(Long id);

}