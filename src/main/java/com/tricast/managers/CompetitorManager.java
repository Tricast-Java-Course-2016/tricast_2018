package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Competitor;;

public interface CompetitorManager {
	
    List<Competitor> findAll();

    Competitor findById(Long id);

    Competitor create(Competitor player);

    Competitor update(Competitor player);

    void deleteById(Long id);
    
}
