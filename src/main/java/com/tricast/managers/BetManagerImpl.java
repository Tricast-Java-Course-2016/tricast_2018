package com.tricast.managers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.BetRequest;
import com.tricast.controllers.responses.BetPlacementResponse;
import com.tricast.controllers.responses.BetResponse;
import com.tricast.managers.mappers.BetResponseMapper;
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

@Service
public class BetManagerImpl implements BetManager {

    private BetRepository betRepository;
    private TransactionRepository transactionRepository;
    private BetTypeRepository bettypeRepository;
    private BetOutcomeMapRepository betoutcomemapRepository;
    private OutcomeRepository outcomeRepository;
    private MarketRepository marketRepository;
    private EventRepository eventRepository;
    private AccountRepository accountRepository;

    @Autowired
    public BetManagerImpl(BetRepository betRepository, 
    		TransactionRepository transactionRepository,
    		BetTypeRepository bettypeRepository,
    		BetOutcomeMapRepository betoutcomemapRepository,
    		OutcomeRepository outcomeRepository,
    		MarketRepository marketRepository,
    		EventRepository eventRepository,
    		AccountRepository accountRepository) {
    	
        this.betRepository = betRepository;
        this.transactionRepository = transactionRepository;
        this.bettypeRepository = bettypeRepository;
        this.betoutcomemapRepository = betoutcomemapRepository;
        this.outcomeRepository = outcomeRepository;
        this.marketRepository = marketRepository;
        this.eventRepository = eventRepository;
        this.accountRepository=accountRepository;
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


   
    @Override
    public BetPlacementResponse create(BetRequest requestObject) {
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
            	transactions.get(i).setCreatedDate(OffsetDateTime.now());
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
        	ArrayList <ArrayList<Long>> combinations;
        	ArrayList <Long> idList = new ArrayList<Long>();
        	BetOutcomeMap currentBetOutcomeMap;
        	
        	for(int i=0;i<NumberOfOutcomes;i++) {
        		currentId=iterator.next();
        		idList.add(currentId);
        	}
        	
        	combinations=getCombinations(idList);
        	
        	for(int i=0;i<combinations.size();i++) {
        		if(marketRepository.findById(outcomeRepository.findById(combinations.get(i).get(0)).getMarket().getId())
            			==	marketRepository.findById(outcomeRepository.findById(combinations.get(i).get(1)).getMarket().getId())) {
        				combinations.remove(i);
            		}
        	}
        	if(combinations.size()==1) combinations.clear();
        	
        	for(int i=0;i<combinations.size();i++) {
        			bets.add(new Bet());
                	bets.get(i).setAccountId(accountRepository.findById(requestObject.getAccountId()));
                	bets.get(i).setBetTypeId(bettypeRepository.findById(requestObject.getBettypeId()));
                	betRepository.save(bets.get(i));
                	
                	currentBetOutcomeMap=new BetOutcomeMap();
                	currentBetOutcomeMap.setBetId(bets.get(i));
                	currentBetOutcomeMap.setOutcomeID(outcomeRepository.findById(combinations.get(i).get(0)));
                	currentBetOutcomeMap.setOdds(requestObject.getOutcomeOdds().get(combinations.get(i).get(0)));                	
                	betoutcomemapRepository.save(currentBetOutcomeMap);
                	
                	currentBetOutcomeMap=new BetOutcomeMap();
                	currentBetOutcomeMap.setBetId(bets.get(i));
                	currentBetOutcomeMap.setOutcomeID(outcomeRepository.findById(combinations.get(i).get(1)));
                	currentBetOutcomeMap.setOdds(requestObject.getOutcomeOdds().get(combinations.get(i).get(1)));                	
                	betoutcomemapRepository.save(currentBetOutcomeMap);
                	
                	
                	transactions.add(new Transaction());
                	transactions.get(i).setAccount(accountRepository.findById(requestObject.getAccountId()));
                	transactions.get(i).setAmount(requestObject.getBetStake().negate());
                	transactions.get(i).setBet(bets.get(i));      
                	transactions.get(i).setDescription(bettypeRepository.findById(requestObject.getBettypeId()).getDescription().getValue()+
                			" Bet "+outcomeRepository.findById(combinations.get(i).get(0)).getDescription()+ " @ "+
                			outcomeRepository.findById(combinations.get(i).get(0)).getOdds()+" "+
                			outcomeRepository.findById(combinations.get(i).get(1)).getDescription()+ " @ "+
                			outcomeRepository.findById(combinations.get(i).get(1)).getOdds());
                	transactions.get(i).setType(TransactionTypes.BET);
                	transactions.get(i).setCreatedDate(OffsetDateTime.now());
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
        else if(bettypeRepository.findById(requestObject.getBettypeId()).getDescription().equals(BetTypes.Treble)) {
        	
        	if(NumberOfOutcomes!=3) {
        		/*You can only place a treble bet with 3 outcomes. Needs some Exception.*/
        		return null;
        	}
        	else {
        		/*Check, so that you can't bet on multiple outcomes of the same market.*/
        		Set <Long> marketIds=new HashSet <Long>();
        		for(int i=0;i<NumberOfOutcomes;i++) {
        			currentId=iterator.next();
        			if(marketIds.contains(outcomeRepository.findById(currentId).getMarket().getId())) {
        				/*Market already used*/
        				return null;
        			}
        			else {
        				marketIds.add(outcomeRepository.findById(currentId).getMarket().getId());
        			}
        		}
        		
            	
        		iterator=outcomeIds.iterator();
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
            	transactions.get(transactions.size()-1).setCreatedDate(OffsetDateTime.now());
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
        /*Bad BetTypeId*/
        else return null;
    	
    }
    
    
    private static ArrayList <ArrayList<Long>> getCombinations(ArrayList <Long> idList){
		ArrayList <ArrayList <Long>> combinations=new ArrayList <ArrayList<Long>>(); 
		ArrayList <Long> current;
    	
    	for(int i=0;i<idList.size()-1;i++) {
    		for(int  j=i+1;j<idList.size();j++) {
    			current=new ArrayList <Long>();
    			current.add(idList.get(i));
    			current.add(idList.get(j));
    			combinations.add(current);
    		}
    	}
    	return combinations;	
    }
    
}
