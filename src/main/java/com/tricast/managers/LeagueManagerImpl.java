package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.responses.LeagueResponse;
import com.tricast.repositories.LeagueRepository;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.League;

@Service
public class LeagueManagerImpl implements LeagueManager {

    @Autowired
    private LeagueRepository leagueRepository;
	
	@Override
	public List<LeagueResponse> findAll() {
		List<LeagueResponse> leagueResponses = new ArrayList<LeagueResponse>();
		for (League league : leagueRepository.findAll()) {
			leagueResponses.add(this.buildResponse(league));
		}
		return leagueResponses;
	}

	@Override
	public LeagueResponse findById(Long id) {
		return this.buildResponse( leagueRepository.findOne(id) );
	}

	@Override
	public League create(League league) {
		return leagueRepository.save(league);
	}

	@Override
	public League update(League league) {
		return leagueRepository.save(league);
	}

	@Override
	public void deleteById(Long id) {
		leagueRepository.delete(id);
	}

	private LeagueResponse buildResponse(League league) {
		LeagueResponse response = new LeagueResponse();
		response.setId(league.getId());
		response.setSportId(league.getSportId());
		response.setDescription(league.getDescription());
		
		List<Long> competitorIds = new ArrayList<Long>();
		for (Competitor competitor : league.getCompetitors()) {
			competitorIds.add(competitor.getId());
		}
		
		response.setCompetitorIds(competitorIds);
		return response;
	}
	
}
