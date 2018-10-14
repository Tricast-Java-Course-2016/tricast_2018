package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.tricast.repositories.entities.Result;

public interface ResultRepository extends CrudRepository<Result, Long> {

	@Override
    List<Result> findAll();

	Result findById(Long id);

}