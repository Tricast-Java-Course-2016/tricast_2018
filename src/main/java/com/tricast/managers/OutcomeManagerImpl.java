package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.entities.Outcome;

@Service
public class OutcomeManagerImpl implements OutcomeManager {

    private OutcomeRepository outcomeRepository;

    @Inject
    public OutcomeManagerImpl(OutcomeRepository outcomeRepository) {
        this.outcomeRepository = outcomeRepository;
    }

    @Override
    public List<Outcome> findAll() {
        return outcomeRepository.findAll();
    }

    @Override
    public Outcome findById(Long id) {
        return outcomeRepository.findById(id);
    }

    @Override
    public Outcome create(Outcome outcome) {
        return outcomeRepository.save(outcome);
    }

    @Override
    public Outcome update(Outcome outcome) {
        return outcomeRepository.save(outcome);
    }

    @Override
    public void deleteById(Long id) {
        outcomeRepository.delete(id);
    }

	@Override
	public List<Outcome> findByEventId(long eventId) {
		List<Outcome> outcomeByEventId = outcomeRepository.findByMarket_Event_Id(eventId);
		return outcomeByEventId;
	}

}