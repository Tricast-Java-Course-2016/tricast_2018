package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.SportRepository;

import com.tricast.repositories.entities.Sport;

@Service
public class SportManagerImpl implements SportManager{
	
	private SportRepository sportRepository;

	@Inject
	public SportManagerImpl(SportRepository sportRepository) {
		this.sportRepository = sportRepository;
	}
	@Override
	public List<Sport> findAll() {
		return sportRepository.findAll();
	}

	@Override
	public Sport findById(Long id) {
		return sportRepository.findById(id);
	}

	@Override
	public Sport create(Sport sport) {
		return sportRepository.save(sport);
	}

	@Override
	public Sport update(Sport sport) {
		return sportRepository.save(sport);
	}

	@Override
	public void deleteById(Long id) {
		sportRepository.delete(id);
		
	}

}