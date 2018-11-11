package com.tricast.managers;

import java.util.List;

import com.tricast.controllers.requests.CompetitorRequest;
import com.tricast.controllers.responses.CompetitorResponse;
import com.tricast.repositories.entities.Competitor;;

public interface CompetitorManager {
	
    List<CompetitorResponse> findAll();

    CompetitorResponse findById(Long id);

    CompetitorResponse create(CompetitorRequest competitorRequest);

    Competitor update(Competitor player);

    void deleteById(Long id);

	List<CompetitorResponse> search(String search);
    
}
