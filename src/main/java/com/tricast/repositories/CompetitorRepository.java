package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Competitor;

public interface CompetitorRepository extends CrudRepository<Competitor, Long> {
	@Override
    List<Competitor> findAll();

    Competitor findById(Long id);
}
