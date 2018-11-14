package com.tricast.managers.mappers;

import java.math.BigDecimal;
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
import com.tricast.repositories.entities.BetOutcomeMap;


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

        List <BetOutcomeMap> betOutcomeMap = betoutcomemapRepository.findByBet_Id(entityObject.getId());
        
        List<String> outcomes = new ArrayList<String>();
        List<String> events = new ArrayList<String>();
        List<String> markets = new ArrayList<String>();
        List<String> statuses = new ArrayList<String>();
        List<Double> odds = new ArrayList<Double>();
        Double sumodds=1.;
        
        
        BetResponse responseObject = new BetResponse();
        responseObject.setBettypeId(entityObject.getBetTypeId().getId());
        responseObject.setAccountId(entityObject.getAccountId().getId());
        
        
        responseObject.setBetStatus("CLOSED");
        for(BetOutcomeMap currentMap : betOutcomeMap) {
        	statuses.add(eventRepository.findById(marketRepository.findById(outcomeRepository.findById(
            		currentMap.getOutcomeID().getId()).getMarket().getId()).getId()).getStatus().getValue());
        	
        	outcomes.add(currentMap.getOutcomeID().getDescription());
        	
        	events.add(eventRepository.findById(marketRepository.findById(outcomeRepository.findById(
            		currentMap.getOutcomeID().getId()).getMarket().getId()).getEvent().getId()).getDescription());
        	
        	markets.add(marketRepository.findById(outcomeRepository.findById(
            		currentMap.getOutcomeID().getId()).getMarket().getId()).getDescription());
        	
        	odds.add(currentMap.getOdds());
        	sumodds*=currentMap.getOdds();	
        }
        
        for(String status : statuses) {
        	if(status.equals("OPEN")) responseObject.setBetStatus("OPEN");
        }
        
        responseObject.setEventStatusList(statuses);
        responseObject.setOutcome(outcomes);
        responseObject.setEvent(events);
        responseObject.setMarketDescription(markets);
        responseObject.setBetType(bettypeRepository.findById(entityObject.getBetTypeId().getId()).getDescription().getValue());
        responseObject.setStake(transactionRepository.findByBet(entityObject).getAmount().negate());
        responseObject.setOdds(odds);      
        responseObject.setSumOdds(sumodds);
        responseObject.setPotentialWin(responseObject.getStake().multiply(BigDecimal.valueOf(responseObject.getSumOdds())));
        
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
