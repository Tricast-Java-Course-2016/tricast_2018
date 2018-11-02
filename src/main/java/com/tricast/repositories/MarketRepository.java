package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.Market;

public interface MarketRepository extends CrudRepository<Market, Long> {

	@Override
    List<Market> findAll();

    Market findById(Long id);
    
    List <Market> findByEvent(Event event);

}