package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.SportsRepository;

import com.tricast.repositories.entities.Sports;

@Service
public class SportsManagerImpl implements SportsManager{
	
	private SportsRepository sportsRepository;

	@Inject
	public SportsManagerImpl(SportsRepository sportsRepository) {
		this.sportsRepository = sportsRepository;
	}
	@Override
	public List<Sports> findAll() {
		return sportsRepository.findAll();
	}

	@Override
	public Sports findById(Long id) {
		return sportsRepository.findById(id);
	}

	@Override
	public Sports create(Sports sport) {
		return sportsRepository.save(sport);
	}

	@Override
	public Sports update(Sports sport) {
		return sportsRepository.save(sport);
	}

	@Override
	public void deleteById(Long id) {
		sportsRepository.delete(id);
		
	}

}