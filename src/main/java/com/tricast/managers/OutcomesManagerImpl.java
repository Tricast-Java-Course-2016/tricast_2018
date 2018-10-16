package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.OutcomesRepository;
import com.tricast.repositories.entities.Outcomes;

@Service
public class OutcomesManagerImpl implements OutcomesManager {

    private OutcomesRepository outcomesRepository;

    @Inject
    public OutcomesManagerImpl(OutcomesRepository outcomeRepository) {
        this.outcomesRepository = outcomeRepository;
    }

    @Override
    public List<Outcomes> findAll() {
        return outcomesRepository.findAll();
    }

    @Override
    public Outcomes findById(Long id) {
        return outcomesRepository.findById(id);
    }

    @Override
    public Outcomes create(Outcomes outcome) {
        return outcomesRepository.save(outcome);
    }

    @Override
    public Outcomes update(Outcomes outcome) {
        return outcomesRepository.save(outcome);
    }

    @Override
    public void deleteById(Long id) {
        outcomesRepository.delete(id);
    }

}