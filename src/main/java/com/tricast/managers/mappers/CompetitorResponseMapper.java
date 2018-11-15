package com.tricast.managers.mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.tricast.controllers.requests.CompetitorRequest;
import com.tricast.controllers.responses.CompetitorResponse;
import com.tricast.repositories.LeagueRepository;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.League;

public class CompetitorResponseMapper {
	@Autowired
	private LeagueRepository leagueRepository;
	
	public CompetitorResponse entityToResponse(Competitor competitor) {
		CompetitorResponse response = new CompetitorResponse();
		response.setId(competitor.getId());
		response.setDescription(competitor.getDescription());
		return response;
	}
	
	public Competitor requestToEntity(CompetitorRequest competitorRequest) {
		Competitor competitor = new Competitor();
		competitor.setDescription(competitorRequest.getDescription());
		
		List<Long> leagueIds = competitorRequest.getLeagueIds();
		Set<League> leagues = new HashSet<League>();
		
		for (Long id : leagueIds) {
			leagues.add(leagueRepository.findById(id));
		}
		return competitor;
	}
}
