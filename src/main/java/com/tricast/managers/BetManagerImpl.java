package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.BetRepository;
import com.tricast.repositories.entities.Bet;

@Service
public class BetManagerImpl implements BetManager {

    private BetRepository betRepository;

    @Inject
    public BetManagerImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Override
    public List<Bet> findAll() {
        return betRepository.findAll();
    }

    @Override
    public Bet findById(Long id) {
        return betRepository.findById(id);
    }

    @Override
    public Bet create(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public Bet update(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public void deleteById(Long id) {
    	betRepository.delete(id);
    }

}
