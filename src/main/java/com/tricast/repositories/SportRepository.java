package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Sport;

public interface SportRepository extends CrudRepository<Sport, Long>{

	@Override
    List<Sport> findAll();

    Sport findById(Long id);

    Sport findByDescriptionLike(String description);
}
