package com.tricast.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.CompetitorRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.ResultRepository;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.Market;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.Result;

@Service
public class OutcomeManagerImpl implements OutcomeManager {

    private OutcomeRepository outcomeRepository;
    private ResultRepository resultRepository;
    private MarketRepository marketRepository;
    private CompetitorRepository competitorRepository;

    @Inject
    public OutcomeManagerImpl(
    						OutcomeRepository outcomeRepository,
    						ResultRepository resultRepository,
    						MarketRepository marketRepository,
							CompetitorRepository competitorRepository) {
        this.outcomeRepository = outcomeRepository;
        this.resultRepository = resultRepository;
        this.marketRepository = marketRepository;
        this.competitorRepository = competitorRepository;
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
		List<Result> resultByEventId = resultRepository.findByEventCompetitorMap_Event_IdOrderByEventCompetitorMap_CompetitorIdAscResultType_IdAscPeriodType_IdAsc(eventId);
		List<Market> marketByEventId = marketRepository.findByEvent_Id(eventId);
		
		Set<Result> resultByEventIdAndMarketType = new HashSet<Result>();
		Map<Long, Integer> competitorWithResult = new HashMap<Long, Integer>();
		
		for(Market currentMarket : marketByEventId) {
			for(Result currentResult : resultByEventId) {
				if(
					currentResult.getResultType().getType() == currentMarket.getMarketType().getType().getResultType()
						&&
					currentResult.getPeriodType().getId() == currentMarket.getPeriodTypeId().getId()) {
					
					competitorWithResult.put(currentResult.getEventCompetitorMap().getCompetitorId(), currentResult.getResult());
				}
			}
			settlementWDW(competitorWithResult, currentMarket.getId(), eventId);
		}
		return resultByEventIdAndMarketType;
	}
	
	private void settlementWDW(Map<Long, Integer> competitorWithResultMap, long marketId, long eventId) {
		//Megfelelő outcome-ok betöltése és updatelése

		List<Outcome> outcomes = outcomeRepository.findByMarket_Id(marketId);
		List<Competitor> competitors = competitorRepository.findByEventId(eventId);
		
		int resultHome = 0;
		long competitorHome = 0;
		int resultAway = 0;
		long competitorAway = 0;
		int status = 0;
		Long winnerCompetitor = null;
		
		for(Competitor currentCompetitor : competitors) {
			if(status == 0) {
				resultHome = competitorWithResultMap.get(currentCompetitor.getId());
				competitorHome = currentCompetitor.getId();
				status++;
			} else {
				resultAway = competitorWithResultMap.get(currentCompetitor.getId());
				competitorAway = currentCompetitor.getId();
			}
		}
		
		if(resultHome > resultAway) {
			winnerCompetitor = competitorHome;
		} else if (resultHome < resultAway) {
			winnerCompetitor = competitorAway;
		} else {
			winnerCompetitor = null;
		}
		
		for(Outcome currentOutcome : outcomes) {
			if(currentOutcome.getCompetitorId() == winnerCompetitor) {
				currentOutcome.setWinYN(1);
			} else {
				currentOutcome.setWinYN(0);
			}
			outcomeRepository.save(currentOutcome);
		}		
	}
}