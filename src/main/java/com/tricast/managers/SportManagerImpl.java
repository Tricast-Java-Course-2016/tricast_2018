package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.controllers.responses.MarketTypeResponse;
import com.tricast.controllers.responses.PeriodTypeResponse;
import com.tricast.controllers.responses.SportResponse;
import com.tricast.repositories.SportRepository;
import com.tricast.repositories.entities.MarketTypeEnum;
import com.tricast.repositories.entities.PeriodTypeEnum;
import com.tricast.repositories.entities.Sport;

@Service
public class SportManagerImpl implements SportManager{
	
	private SportRepository sportRepository;

	@Inject
	public SportManagerImpl(SportRepository sportRepository) {
		this.sportRepository = sportRepository;
	}
	@Override
	public List<SportResponse> findAll() {
		List<Sport> sports = sportRepository.findAll();
		
		List<SportResponse> responses = new ArrayList<SportResponse>();
		
		for (Sport sport : sports) {
			responses.add(this.buildResponse(sport));
		}
		
		return responses;
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

	private SportResponse buildResponse(Sport sport) {
		SportResponse sportResponse = new SportResponse();
		sportResponse.setId(sport.getId());
		sportResponse.setDescription(sport.getDescription());
		
		List<MarketTypeResponse> marketTypeResponses = new ArrayList<MarketTypeResponse>();
		for (MarketTypeEnum marketTypeEnum : sport.getType().getMarketTypes()) {
			MarketTypeResponse marketTypeResponse = new MarketTypeResponse();
			marketTypeResponse.setId(marketTypeEnum.getId());
			marketTypeResponse.setDescription(marketTypeEnum.getDescription());
			marketTypeResponses.add(marketTypeResponse);
		}
		
		List<PeriodTypeResponse> periodTypeResponses = new ArrayList<PeriodTypeResponse>();
		for (PeriodTypeEnum periodTypeEnum : sport.getType().getPeriodTypes()) {
			PeriodTypeResponse periodTypeResponse = new PeriodTypeResponse();
			periodTypeResponse.setId(periodTypeEnum.getId());
			periodTypeResponse.setDescription(periodTypeEnum.getDescription());
			periodTypeResponses.add(periodTypeResponse);
		}
		
		sportResponse.setMarketTypes(marketTypeResponses);
		sportResponse.setPeriodTypes(periodTypeResponses);
		
		return sportResponse;
	}
	
}