package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.tricast.repositories.entities.Results;

public interface ResultsRepository extends CrudRepository<Results, Long> {

	@Override
    List<Results> findAll();

	Results findById(Long id);

}