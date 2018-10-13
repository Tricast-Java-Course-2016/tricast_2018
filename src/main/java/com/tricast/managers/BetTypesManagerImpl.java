package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.BetTypesRepository;
import com.tricast.repositories.entities.BetTypes;

@Service
public class BetTypesManagerImpl implements BetTypesManager {

    private BetTypesRepository bettypesRepository;

    @Inject
    public BetTypesManagerImpl(BetTypesRepository bettypesRepository) {
        this.bettypesRepository = bettypesRepository;
    }

    @Override
    public List<BetTypes> findAll() {
        return bettypesRepository.findAll();
    }

    @Override
    public BetTypes findById(Long id) {
        return bettypesRepository.findById(id);
    }

    @Override
    public BetTypes create(BetTypes bettype) {
        return bettypesRepository.save(bettype);
    }

    @Override
    public BetTypes update(BetTypes bettype) {
        return bettypesRepository.save(bettype);
    }

    @Override
    public void deleteById(Long id) {
    	bettypesRepository.delete(id);
    }

}
