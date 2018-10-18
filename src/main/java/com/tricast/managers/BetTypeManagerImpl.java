package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.BetTypeRepository;
import com.tricast.repositories.entities.BetType;

@Service
public class BetTypeManagerImpl implements BetTypeManager {

    private BetTypeRepository bettypeRepository;

    @Inject
    public BetTypeManagerImpl(BetTypeRepository bettypeRepository) {
        this.bettypeRepository = bettypeRepository;
    }

    @Override
    public List<BetType> findAll() {
        return bettypeRepository.findAll();
    }

    @Override
    public BetType findById(Long id) {
        return bettypeRepository.findById(id);
    }

    @Override
    public BetType create(BetType bettype) {
        return bettypeRepository.save(bettype);
    }

    @Override
    public BetType update(BetType bettype) {
        return bettypeRepository.save(bettype);
    }

    @Override
    public void deleteById(Long id) {
    	bettypeRepository.delete(id);
    }

}
