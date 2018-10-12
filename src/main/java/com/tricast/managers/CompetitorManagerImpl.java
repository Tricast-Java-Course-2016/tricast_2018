package com.tricast.managers;

import java.util.List;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.CompetitorRepository;

@Service
public class CompetitorManagerImpl implements CompetitorManager {
	@Autowired
	private CompetitorRepository competitorRepository;

    @Override
    public List<Competitor> findAll() {
        return competitorRepository.findAll();
    }

    @Override
    public Competitor findById(Long id) {
        return competitorRepository.findById(id);
    }

    @Override
    public Competitor create(Competitor player) {
        return competitorRepository.save(player);
    }

    @Override
    public Competitor update(Competitor player) {
        return competitorRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        competitorRepository.delete(id);
    }

}
