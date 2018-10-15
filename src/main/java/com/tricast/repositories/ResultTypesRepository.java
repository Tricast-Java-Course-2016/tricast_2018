package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.ResultTypes;

public interface ResultTypesRepository extends CrudRepository<ResultTypes, Long>{

	@Override
    List<ResultTypes> findAll();

    ResultTypes findById(Long id);

}
