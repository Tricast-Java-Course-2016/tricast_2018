package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.MarketTypeRepository;
import com.tricast.repositories.entities.MarketType;

@Service
public class MarketTypeManagerImpl implements MarketTypeManager {

    private MarketTypeRepository markettypeRepository;

    @Inject
    public MarketTypeManagerImpl(MarketTypeRepository markettypeRepository) {
        this.markettypeRepository = markettypeRepository;
    }

    @Override
    public List<MarketType> findAll() {
        return markettypeRepository.findAll();
    }

    @Override
    public MarketType findById(Long id) {
        return markettypeRepository.findById(id);
    }

    @Override
    public MarketType create(MarketType markettype) {
        return markettypeRepository.save(markettype);
    }

    @Override
    public MarketType update(MarketType markettype) {
        return markettypeRepository.save(markettype);
    }

    @Override
    public void deleteById(Long id) {
    	markettypeRepository.delete(id);
    }

}