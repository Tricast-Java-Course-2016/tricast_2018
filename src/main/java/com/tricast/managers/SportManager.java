package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Sport;

public interface SportManager {
	
	List<Sport> findAll();
	
	Sport findById(Long id);
	
	Sport create(Sport sport);
	
	Sport update(Sport sport);
	
	void deleteById(Long id);
}