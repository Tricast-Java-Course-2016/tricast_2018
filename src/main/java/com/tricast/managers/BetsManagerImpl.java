package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.BetsRepository;
import com.tricast.repositories.entities.Bets;

@Service
public class BetsManagerImpl implements BetsManager {

    private BetsRepository betsRepository;

    @Inject
    public BetsManagerImpl(BetsRepository betsRepository) {
        this.betsRepository = betsRepository;
    }

    @Override
    public List<Bets> findAll() {
        return betsRepository.findAll();
    }

    @Override
    public Bets findById(Long id) {
        return betsRepository.findById(id);
    }

    @Override
    public Bets create(Bets bet) {
        return betsRepository.save(bet);
    }

    @Override
    public Bets update(Bets bet) {
        return betsRepository.save(bet);
    }

    @Override
    public void deleteById(Long id) {
    	betsRepository.delete(id);
    }

}
