package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.MarketsRepository;
import com.tricast.repositories.entities.Markets;

@Service
public class MarketsManagerImpl implements MarketsManager {

    private MarketsRepository marketsRepository;

    @Inject
    public MarketsManagerImpl(MarketsRepository marketRepository) {
        this.marketsRepository = marketRepository;
    }

    @Override
    public List<Markets> findAll() {
        return marketsRepository.findAll();
    }

    @Override
    public Markets findById(Long id) {
        return marketsRepository.findById(id);
    }

    @Override
    public Markets create(Markets market) {
        return marketsRepository.save(market);
    }

    @Override
    public Markets update(Markets market) {
        return marketsRepository.save(market);
    }

    @Override
    public void deleteById(Long id) {
        marketsRepository.delete(id);
    }

}