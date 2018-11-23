package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.BetOutcomeMapRepository;
import com.tricast.repositories.entities.BetOutcomeMap;

@Service
public class BetOutcomeMapManagerImpl implements BetOutcomeMapManager {

    private BetOutcomeMapRepository betoutcomemapRepository;

    @Inject
    public BetOutcomeMapManagerImpl(BetOutcomeMapRepository bomapRepository) {
        this.betoutcomemapRepository = bomapRepository;
    }

    @Override
    public List<BetOutcomeMap> findAll() {
        return betoutcomemapRepository.findAll();
    }

    @Override
    public BetOutcomeMap findById(Long id) {
        return betoutcomemapRepository.findById(id);
    }

    @Override
    public BetOutcomeMap create(BetOutcomeMap betoutcomemap) {
        return betoutcomemapRepository.save(betoutcomemap);
    }

    @Override
    public BetOutcomeMap update(BetOutcomeMap betoutcomemap) {
        return betoutcomemapRepository.save(betoutcomemap);
    }

    @Override
    public void deleteById(Long id) {
    	betoutcomemapRepository.delete(id);
    }
}
