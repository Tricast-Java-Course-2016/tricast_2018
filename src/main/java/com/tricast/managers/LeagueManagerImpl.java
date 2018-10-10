package com.tricast.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.repositories.LeagueRepository;
import com.tricast.repositories.entities.League;

@Service
public class LeagueManagerImpl implements LeagueManager {

    @Autowired
    private LeagueRepository leagueRepository;
	
	@Override
	public List<League> findAll() {
		return leagueRepository.findAll();
	}

	@Override
	public League findById(Long id) {
		return leagueRepository.findOne(id);
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

}
