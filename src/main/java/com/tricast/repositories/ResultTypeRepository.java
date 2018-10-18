package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.ResultType;

public interface ResultTypeRepository extends CrudRepository<ResultType, Long>{

	@Override
    List<ResultType> findAll();

    ResultType findById(Long id);

}
