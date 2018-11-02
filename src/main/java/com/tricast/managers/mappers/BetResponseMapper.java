package com.tricast.managers.mappers;

import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.BetResponse;
import com.tricast.repositories.BetOutcomeMapRepository;
import com.tricast.repositories.BetRepository;
import com.tricast.repositories.BetTypeRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.TransactionRepository;
import com.tricast.repositories.entities.Bet;


public class BetResponseMapper {

    public static BetResponse mapToResponse(Bet entityObject, BetRepository betRepository, 
    		TransactionRepository transactionRepository,
    		BetTypeRepository bettypeRepository,
    		BetOutcomeMapRepository betoutcomemapRepository,
    		OutcomeRepository outcomeRepository,
    		MarketRepository marketRepository,
    		EventRepository eventRepository) {
        if(entityObject == null) {
            return null;
        }

        int numberOfOutcomes=betoutcomemapRepository.findByBet(entityObject).size();
        
        BetResponse responseObject = new BetResponse();
        responseObject.setBettypeId(entityObject.getBetTypeId().getId());
        responseObject.setAccountId(entityObject.getAccountId().getId());
        
        /*Needs more work (toString) when Event status Enum is done.*/
        List<String> statuses = new ArrayList<String>();
        
        for(int i=0;i<numberOfOutcomes;i++) {
        	statuses.add(eventRepository.findById(marketRepository.findById(outcomeRepository.findById(
            		betoutcomemapRepository.findByBet(
            				entityObject).get(i).getOutcomeID().getId()).getMarketId().getId()).getId()).getStatus());
        }
        
        responseObject.setBetStatus(statuses);
        
        List<String> outcomes = new ArrayList<String>();
        
        for(int i=0;i<numberOfOutcomes;i++) {
        	outcomes.add(betoutcomemapRepository.findByBet(
        			entityObject).get(i).getOutcomeID().getDescription());
        }
        responseObject.setOutcome(outcomes);
        
        
        List<String> events = new ArrayList<String>();
        
        for(int i=0;i<numberOfOutcomes;i++) {
        	events.add(eventRepository.findById(marketRepository.findById(outcomeRepository.findById(
        		betoutcomemapRepository.findByBet(
        				entityObject).get(i).getOutcomeID().getId()).getMarketId().getId()).getEvent().getId()).getDescription());
        }
        responseObject.setEvent(events);
       
        
        List<String> markets = new ArrayList<String>();
        
        for(int i=0;i<numberOfOutcomes;i++) {
        	markets.add(marketRepository.findById(outcomeRepository.findById(
        		betoutcomemapRepository.findByBet(
        				entityObject).get(i).getOutcomeID().getId()).getMarketId().getId()).getDescription());
        }
        responseObject.setMarketDescription(markets);
        
        
        responseObject.setBetType(bettypeRepository.findById(entityObject.getBetTypeId().getId()).getDescription().getValue());
        
        
        
        responseObject.setStake(transactionRepository.findByBet(entityObject).getAmount()*-1);
        
        List<Double> odds = new ArrayList<Double>();
        Double sumodds=1.;
        
        for(int i=0;i<numberOfOutcomes;i++) {
        	odds.add(betoutcomemapRepository.findByBet(entityObject).get(i).getOdds());
        	sumodds*=betoutcomemapRepository.findByBet(entityObject).get(i).getOdds();
        }
        responseObject.setOdds(odds);
        
        responseObject.setSumOdds(sumodds);
        
        responseObject.setPotentialWin(responseObject.getStake()*responseObject.getSumOdds());
        
        return responseObject;
    }

    public static List<BetResponse> mapToBeanList(List<Bet> entityObjectsList, 
    		BetRepository betRepository, 
    		TransactionRepository transactionRepository,
    		BetTypeRepository bettypeRepository,
    		BetOutcomeMapRepository betoutcomemapRepository,
    		OutcomeRepository outcomeRepository,
    		MarketRepository marketRepository,
    		EventRepository eventRepository) {

        if(entityObjectsList == null) {
            return null;
        }

        List<BetResponse> betsResponseList = new ArrayList<>();

        entityObjectsList.forEach(entity -> betsResponseList.add(mapToResponse(entity, betRepository, 
        		transactionRepository,
        		bettypeRepository,
        		betoutcomemapRepository,
        		outcomeRepository,
        		marketRepository,
        		eventRepository)));

        return betsResponseList;
    }
}
