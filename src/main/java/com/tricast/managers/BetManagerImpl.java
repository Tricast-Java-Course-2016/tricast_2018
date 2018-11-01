package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.BetRequest;
import com.tricast.controllers.responses.BetResponse;
import com.tricast.managers.mappers.BetResponseMapper;
import com.tricast.repositories.BetOutcomeMapRepository;
import com.tricast.repositories.BetRepository;
import com.tricast.repositories.BetTypeRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.TransactionRepository;
import com.tricast.repositories.entities.Bet;

@Service
public class BetManagerImpl implements BetManager {

    private BetRepository betRepository;
    private TransactionRepository transactionRepository;
    private BetTypeRepository bettypeRepository;
    private BetOutcomeMapRepository betoutcomemapRepository;
    private OutcomeRepository outcomeRepository;
    private MarketRepository marketRepository;
    private EventRepository eventRepository;

    @Autowired
    public BetManagerImpl(BetRepository betRepository, 
    		TransactionRepository transactionRepository,
    		BetTypeRepository bettypeRepository,
    		BetOutcomeMapRepository betoutcomemapRepository,
    		OutcomeRepository outcomeRepository,
    		MarketRepository marketRepository,
    		EventRepository eventRepository) {
    	
        this.betRepository = betRepository;
        this.transactionRepository = transactionRepository;
        this.bettypeRepository = bettypeRepository;
        this.betoutcomemapRepository = betoutcomemapRepository;
        this.outcomeRepository = outcomeRepository;
        this.marketRepository = marketRepository;
        this.eventRepository = eventRepository;
    }
    
  

    @Override
    public List<BetResponse> findAll() {
        return BetResponseMapper.mapToBeanList(betRepository.findAll(), 
        		betRepository, 
        		transactionRepository,
        		bettypeRepository,
        		betoutcomemapRepository,
        		outcomeRepository,
        		marketRepository,
        		eventRepository);
    }

    @Override
    public BetResponse findById(Long id) {

		Bet bet = betRepository.findById(id);

		BetResponse betBean = BetResponseMapper.mapToResponse(bet, 
				betRepository, 
        		transactionRepository,
        		bettypeRepository,
        		betoutcomemapRepository,
        		outcomeRepository,
        		marketRepository,
        		eventRepository);

		return betBean;
    }


    /*@Override
    public BetResponse create(BetRequest newBet) {
		Bet bet = BetRequestMapper.mapToEntity(betRequest, new Bet());
		
		return BetResponseMapper.mapToResponse(betRepository.save(bet));

    }*/
    
}
