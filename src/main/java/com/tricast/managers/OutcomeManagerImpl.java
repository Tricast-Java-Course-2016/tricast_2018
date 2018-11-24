package com.tricast.managers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.ResultRepository;
import com.tricast.repositories.entities.Market;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.Result;

@Service
public class OutcomeManagerImpl implements OutcomeManager {

    private OutcomeRepository outcomeRepository;
    private ResultRepository resultRepository;
    private MarketRepository marketRepository;

    @Inject
    public OutcomeManagerImpl(
    						OutcomeRepository outcomeRepository,
    						ResultRepository resultRepository,
    						MarketRepository marketRepository) {
        this.outcomeRepository = outcomeRepository;
        this.resultRepository = resultRepository;
        this.marketRepository = marketRepository;
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
	public Set<Result> findByEventId(long eventId) {
		List<Outcome> outcomeByEventId = outcomeRepository.findByMarket_Event_Id(eventId);
		List<Result> resultByEventId = resultRepository.findByEventCompetitorMap_Event_Id(eventId);
		List<Market> marketByEventId = marketRepository.findByEvent_Id(eventId);

		Set<Result> resultByEventIdAndMarketType = new HashSet<Result>();
		
		for(Result currentResult : resultByEventId) {
			for(Market currentMarket : marketByEventId) {
				if(
					currentResult.getPeriodType().getType() == currentMarket.getMarketType().getType().getPeriodType()
						&&
					currentResult.getResultType().getType() == currentMarket.getMarketType().getType().getResultType()) {
					resultByEventIdAndMarketType.add(currentResult);
				}
			}
		}

		
		return resultByEventIdAndMarketType;
	}

}