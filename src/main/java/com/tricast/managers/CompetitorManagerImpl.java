package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;
import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.League;
import com.tricast.repositories.entities.LeagueCompetitorMap;
import com.tricast.controllers.requests.CompetitorRequest;
import com.tricast.controllers.responses.CompetitorResponse;
import com.tricast.managers.mappers.CompetitorResponseMapper;
import com.tricast.repositories.CompetitorRepository;
import com.tricast.repositories.LeagueCompetitorMapRepository;

@Service
public class CompetitorManagerImpl implements CompetitorManager {
	
	@Autowired
	private CompetitorRepository competitorRepository;

	@Autowired
	private LeagueCompetitorMapRepository leagueCompetitorMapRepository;

	@Override
    public CompetitorResponse findById(Long id) {
        return this.entityToResponse( competitorRepository.findById(id) );
    }
	
    @Override
    public List<CompetitorResponse> findAll() {
    	List<CompetitorResponse> competitorRepsonses = new ArrayList<CompetitorResponse>();
    	for (Competitor competitor : competitorRepository.findAll()) {
			competitorRepsonses.add(this.entityToResponse(competitor));
		}
        return competitorRepsonses;
    }
    
    @Override
	public List<CompetitorResponse> search(String search) {
    	List<CompetitorResponse> competitorRepsonses = new ArrayList<CompetitorResponse>();
    	for (Competitor competitor : competitorRepository.findByDescriptionIgnoreCaseLike(search)) {
			competitorRepsonses.add(this.entityToResponse(competitor));
		}
        return competitorRepsonses;
	}
    
    @Override
    public CompetitorResponse create(CompetitorRequest competitorRequest) {
    	Competitor competitor = new Competitor();
    	competitor.setDescription(competitorRequest.getDescription());
    	
    	competitor = competitorRepository.save(competitor);
    	
    	for (Long leagueId : competitorRequest.getLeagueIds()) {
    		LeagueCompetitorMap leagueCompetitorMap = new LeagueCompetitorMap();
    		leagueCompetitorMap.setCompetitorId(competitor.getId());
    		leagueCompetitorMap.setLeagueId(leagueId);
    		leagueCompetitorMapRepository.save(leagueCompetitorMap);
		}
    	
        return this.findById(competitor.getId());
    }

    @Override
    public Competitor update(Competitor player) {
        return competitorRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        competitorRepository.delete(id);
    }
    
    private CompetitorResponse entityToResponse(Competitor competitor) {
		CompetitorResponse response = new CompetitorResponse();
		response.setId(competitor.getId());
		response.setDescription(competitor.getDescription());
		List<Long> leagueIds = new ArrayList<Long>();
		for (League league : competitor.getLeagues()) {
			leagueIds.add(league.getId());
		}
		response.setLeagueIds(leagueIds);
		return response;
	}

	

}
