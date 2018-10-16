package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Sports;

public interface SportsManager {
	
	List<Sports> findAll();
	
	Sports findById(Long id);
	
	Sports create(Sports sport);
	
	Sports update(Sports sport);
	
	void deleteById(Long id);
}