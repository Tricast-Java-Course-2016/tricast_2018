package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Markets;

public interface MarketsRepository extends CrudRepository<Markets, Long> {

	@Override
    List<Markets> findAll();

    Markets findById(Long id);

}