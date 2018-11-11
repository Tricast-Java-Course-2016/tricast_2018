package com.tricast.managers.mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.tricast.controllers.requests.CompetitorRequest;
import com.tricast.controllers.responses.CompetitorResponse;
import com.tricast.managers.LeagueManager;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.League;

public class CompetitorResponseMapper {
	@Autowired
	private LeagueManager leagueManager;
	
<<<<<<< HEAD
	public CompetitorResponse entityToResponse(Competitor competitor) {
=======
	public static CompetitorResponse entityToResponse(Competitor competitor) {
>>>>>>> SB-42 Résztvevő létrehozása
		CompetitorResponse response = new CompetitorResponse();
		response.setId(competitor.getId());
		response.setDescription(competitor.getDescription());
		return response;
	}
	
<<<<<<< HEAD
	public Competitor requestToEntity(CompetitorRequest competitorRequest) {
=======
	public static Competitor requestToEntity(CompetitorRequest competitorRequest) {
>>>>>>> SB-42 Résztvevő létrehozása
		Competitor competitor = new Competitor();
		competitor.setDescription(competitorRequest.getDescription());
		
		List<Long> leagueIds = competitorRequest.getLeagueIds();
		Set<League> leagues = new HashSet<League>();
		
		for (Long id : leagueIds) {
			leagues.add(leagueManager.findById(id));
		}
		return competitor;
	}
}
