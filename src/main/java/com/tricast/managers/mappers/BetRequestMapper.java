package com.tricast.managers.mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.tricast.controllers.requests.BetRequest;
import com.tricast.controllers.responses.BetPlacementResponse;
import com.tricast.controllers.responses.BetResponse;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.BetOutcomeMapRepository;
import com.tricast.repositories.BetRepository;
import com.tricast.repositories.BetTypeRepository;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.TransactionRepository;
import com.tricast.repositories.entities.Bet;
import com.tricast.repositories.entities.BetOutcomeMap;
import com.tricast.repositories.entities.BetTypes;
import com.tricast.repositories.entities.Transaction;
import com.tricast.repositories.entities.TransactionTypes;

public class BetRequestMapper {

    public static BetPlacementResponse mapToEntity(BetRequest requestObject,
    		BetRepository betRepository, 
    		AccountRepository accountRepository, 
    		BetTypeRepository bettypeRepository,
    		BetOutcomeMapRepository betoutcomemapRepository,
    		OutcomeRepository outcomeRepository,
    		TransactionRepository transactionRepository,
    		EventRepository eventRepository,
    		MarketRepository marketRepository) {

        if(requestObject == null) {
            return null;
        }
        
        BetPlacementResponse betPlacementResponse = new BetPlacementResponse();
        betPlacementResponse.setSumStake(BigDecimal.valueOf(0.));
        betPlacementResponse.setSumPotentialWin(BigDecimal.valueOf(0.));
        
        List <BetResponse> betResponses=new ArrayList <BetResponse>();
        int NumberOfOutcomes=requestObject.getOutcomeOdds().size();
        List <Bet> bets=new ArrayList <Bet>();
        List <Transaction> transactions=new ArrayList <Transaction>();
        List <BetOutcomeMap> betOutcomeMap=new ArrayList <BetOutcomeMap>();
        Set <Long> outcomeIds=requestObject.getOutcomeOdds().keySet(); 
    	Long currentId;
    	Iterator <Long> iterator=outcomeIds.iterator();
        
        if(bettypeRepository.findById(requestObject.getBettypeId()).getDescription().equals(BetTypes.Single)) {
        	
            for(int i=0;i<NumberOfOutcomes;i++) {
            	currentId=iterator.next();
            	bets.add(new Bet());
            	bets.get(i).setAccountId(accountRepository.findById(requestObject.getAccountId()));
            	bets.get(i).setBetTypeId(bettypeRepository.findById(requestObject.getBettypeId()));
            	betRepository.save(bets.get(i));
            	
            	betOutcomeMap.add(new BetOutcomeMap());
            	betOutcomeMap.get(i).setBetId(bets.get(i));
            	betOutcomeMap.get(i).setOutcomeID(outcomeRepository.findById(currentId));
            	betOutcomeMap.get(i).setOdds(requestObject.getOutcomeOdds().get(currentId));
            	betoutcomemapRepository.save(betOutcomeMap.get(i));
            	
            	transactions.add(new Transaction());
            	transactions.get(i).setAccount(accountRepository.findById(requestObject.getAccountId()));
            	transactions.get(i).setAmount(requestObject.getBetStake().negate());
            	transactions.get(i).setBet(bets.get(i));      
            	transactions.get(i).setDescription(bettypeRepository.findById(requestObject.getBettypeId()).getDescription().getValue()+
            			" Bet "+outcomeRepository.findById(currentId).getDescription()+ " @ "+
            			outcomeRepository.findById(currentId).getOdds());
            	transactions.get(i).setType(TransactionTypes.BET);
            	transactions.get(i).setCreatedDate(Calendar.getInstance(Locale.getDefault()));
            	transactionRepository.save(transactions.get(i));
            	
            	betResponses.add(BetResponseMapper.mapToResponse(bets.get(i), betRepository, 
            			transactionRepository, bettypeRepository, 
            			betoutcomemapRepository, outcomeRepository, 
            			marketRepository, eventRepository));
            	betPlacementResponse.setSumStake(betPlacementResponse.getSumStake().add(requestObject.getBetStake()));
            	betPlacementResponse.setSumPotentialWin(betPlacementResponse.getSumPotentialWin().add(
            			betResponses.get(betResponses.size()-1).getPotentialWin()));
            	
            }
            betPlacementResponse.setListOfBetResponses(betResponses);
 
    		return betPlacementResponse;	
        }
        else if(bettypeRepository.findById(requestObject.getBettypeId()).getDescription().equals(BetTypes.Double)) {
        	return null;
        }
        else if(bettypeRepository.findById(requestObject.getBettypeId()).getDescription().equals(BetTypes.Treble)) {
        	
        	if(NumberOfOutcomes!=3) {
        		/*You can only place a treble bet with 3 outcomes. Needs some Exception.*/
        		return null;
        	}
        	else {
        		/*Check needed, so that you can't bet on multiple outcomes of the same market.*/
        		
            	bets.add(new Bet());
            	bets.get(bets.size()-1).setAccountId(accountRepository.findById(requestObject.getAccountId()));
            	bets.get(bets.size()-1).setBetTypeId(bettypeRepository.findById(requestObject.getBettypeId()));
            	betRepository.save(bets.get(bets.size()-1));
            	
            	transactions.add(new Transaction());
            	transactions.get(transactions.size()-1).setDescription(
            			bettypeRepository.findById(requestObject.getBettypeId()).getDescription().getValue()+
            			" Bet ");
            	
                for(int i=0;i<NumberOfOutcomes;i++) {
                	currentId=iterator.next();

                	betOutcomeMap.add(new BetOutcomeMap());
                	betOutcomeMap.get(i).setBetId(bets.get(bets.size()-1));
                	betOutcomeMap.get(i).setOutcomeID(outcomeRepository.findById(currentId));
                	betOutcomeMap.get(i).setOdds(requestObject.getOutcomeOdds().get(currentId));
                	betoutcomemapRepository.save(betOutcomeMap.get(i));
                	transactions.get(transactions.size()-1).setDescription(transactions.get(transactions.size()-1).getDescription()
                		+outcomeRepository.findById(currentId).getDescription()+ " @ "+
            			outcomeRepository.findById(currentId).getOdds() +" ");
                	            	
                }
               	transactions.get(transactions.size()-1).setAccount(accountRepository.findById(requestObject.getAccountId()));
            	transactions.get(transactions.size()-1).setAmount(requestObject.getBetStake().negate());
            	transactions.get(transactions.size()-1).setBet(bets.get(bets.size()-1));      
            	
            	transactions.get(transactions.size()-1).setType(TransactionTypes.BET);
            	transactions.get(transactions.size()-1).setCreatedDate(Calendar.getInstance(Locale.getDefault()));
            	transactionRepository.save(transactions.get(transactions.size()-1));
            	
            	betResponses.add(BetResponseMapper.mapToResponse(bets.get(bets.size()-1), betRepository, 
            			transactionRepository, bettypeRepository, 
            			betoutcomemapRepository, outcomeRepository, 
            			marketRepository, eventRepository));
            	betPlacementResponse.setSumStake(betPlacementResponse.getSumStake().add(requestObject.getBetStake()));
            	betPlacementResponse.setSumPotentialWin(betPlacementResponse.getSumPotentialWin().add(
            			betResponses.get(betResponses.size()-1).getPotentialWin()));
                
                betPlacementResponse.setListOfBetResponses(betResponses);
     
        		return betPlacementResponse;
        	}
        }
        else return null;

    }
}
