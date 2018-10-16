package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Sports;

public interface SportsRepository extends CrudRepository<Sports, Long>{

	@Override
    List<Sports> findAll();

    Sports findById(Long id);

}
