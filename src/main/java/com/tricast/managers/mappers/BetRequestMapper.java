package com.tricast.managers.mappers;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.tricast.controllers.requests.BetRequest;
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
import com.tricast.repositories.entities.Transaction;
import com.tricast.repositories.entities.TransactionTypes;

public class BetRequestMapper {

    public static List <Bet> mapToEntity(BetRequest requestObject,
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
        
        int NumberOfOutcomes=requestObject.getOutcomeOdds().size();
        List <Bet> bets=new ArrayList <Bet>();
        List <Transaction> transactions=new ArrayList <Transaction>();
        List <BetOutcomeMap> betOutcomeMap=new ArrayList <BetOutcomeMap>();
        Set <Long> outcomeIds=requestObject.getOutcomeOdds().keySet(); 
    	Long currentId;
        
    	Iterator <Long> iterator=outcomeIds.iterator();
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
        	transactions.get(i).setDescription("Description.......");
        	transactions.get(i).setType(TransactionTypes.Bet);
        	transactions.get(i).setCreatedDate(OffsetDateTime.now());
        	transactionRepository.save(transactions.get(i));
        	
        }
        
		return null;

    }
}
