package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.entities.Market;

@Service
public class MarketManagerImpl implements MarketManager {

    private MarketRepository marketRepository;

    @Inject
    public MarketManagerImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    @Override
    public List<Market> findAll() {
        return marketRepository.findAll();
    }

    @Override
    public Market findById(Long id) {
        return marketRepository.findById(id);
    }

    @Override
    public Market create(Market market) {
        return marketRepository.save(market);
    }

    @Override
    public Market update(Market market) {
        return marketRepository.save(market);
    }

    @Override
    public void deleteById(Long id) {
        marketRepository.delete(id);
    }

}