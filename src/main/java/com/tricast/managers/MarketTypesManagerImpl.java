package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.MarketTypesRepository;
import com.tricast.repositories.entities.MarketTypes;

@Service
public class MarketTypesManagerImpl implements MarketTypesManager {

    private MarketTypesRepository markettypesRepository;

    @Inject
    public MarketTypesManagerImpl(MarketTypesRepository markettypesRepository) {
        this.markettypesRepository = markettypesRepository;
    }

    @Override
    public List<MarketTypes> findAll() {
        return markettypesRepository.findAll();
    }

    @Override
    public MarketTypes findById(Long id) {
        return markettypesRepository.findById(id);
    }

    @Override
    public MarketTypes create(MarketTypes markettype) {
        return markettypesRepository.save(markettype);
    }

    @Override
    public MarketTypes update(MarketTypes markettype) {
        return markettypesRepository.save(markettype);
    }

    @Override
    public void deleteById(Long id) {
    	markettypesRepository.delete(id);
    }

}