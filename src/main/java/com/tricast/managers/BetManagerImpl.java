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
import org.springframework.transaction.annotation.Transactional;

import com.tricast.controllers.requests.BetRequest;
import com.tricast.controllers.responses.BetPlacementResponse;
import com.tricast.controllers.responses.BetResponse;
import com.tricast.managers.exceptions.SportsbookException;
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
	public BetResponse findByEventId(long id) {
		return null;
	}

    @Override
    public BetPlacementResponse create(BetRequest requestObject) throws SportsbookException {
        if(requestObject == null) {
            return null;
        }
        
        String betType=bettypeRepository.findById(requestObject.getBettypeId()).getDescription().getValue();
        
        if(betType.equals(BetTypes.Single.getValue())) {
        	try {
            	return placeSingleBets(requestObject);
        	}
        	catch(SportsbookException e) {
        		throw e;
        	}

        }
        else if(betType.equals(BetTypes.Double.getValue())) {
        	try {
            	return placeDoubleBets(requestObject);
        	}
        	catch(SportsbookException e) {
        		throw e;
        	}
        }
        else if(betType.equals(BetTypes.Treble.getValue())) {
        	try {
            	return placeTrebleBets(requestObject);
        	}
        	catch(SportsbookException e) {
        		throw e;
        	}
        }
        else {
        	/*Doesn't work.*/
        	throw new SportsbookException("Failed to place Treble Bet due to bad BetTypeID.");  
        }   	
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
    
    @Transactional    
	private BetPlacementResponse placeSingleBets(BetRequest requestObject) throws SportsbookException {
    	
    	Bet currentBet;
    	
        BetPlacementResponse betPlacementResponse = new BetPlacementResponse();
        betPlacementResponse.setSumStake(BigDecimal.valueOf(0.));
        betPlacementResponse.setSumPotentialWin(BigDecimal.valueOf(0.));
        
        List <BetResponse> betResponses=new ArrayList <BetResponse>();
        int NumberOfOutcomes=requestObject.getOutcomeOdds().size();
        Set <Long> outcomeIds=requestObject.getOutcomeOdds().keySet(); 
    	Long currentId;
    	Iterator <Long> iterator=outcomeIds.iterator();
    	List <Long> outcomeIdList;
    	

        for(int i=0;i<NumberOfOutcomes;i++) {
        	
        	currentId=iterator.next();

        	try {
        		currentBet=createBetEntry(requestObject);
        		betRepository.save(currentBet);
        	
        		outcomeIdList = new ArrayList <Long>();
        		outcomeIdList.add(currentId);
        	
        		betoutcomemapRepository.save(createBetOutcomeMapEntry(currentId,requestObject,currentBet));
        		transactionRepository.save(createTransactionEntry(requestObject,currentBet,outcomeIdList));
          

        		betResponses.add(BetResponseMapper.mapToResponse(currentBet, betRepository, 
        				transactionRepository, bettypeRepository, 
        				betoutcomemapRepository, outcomeRepository, 
        				marketRepository, eventRepository));
        		betPlacementResponse.setSumStake(betPlacementResponse.getSumStake().add(requestObject.getBetStake()));
        		betPlacementResponse.setSumPotentialWin(betPlacementResponse.getSumPotentialWin().add(
        				betResponses.get(betResponses.size()-1).getPotentialWin()));
        	}catch(SportsbookException e) {
        		throw e;
        	}
        	catch(Exception e) {
        		SportsbookException exception = new SportsbookException("Failed to place Single Bet.");
        		throw exception;
        	}
        }
        betPlacementResponse.setListOfBetResponses(betResponses);

		return betPlacementResponse;	
    	
    }
    
    @Transactional  
    private BetPlacementResponse placeDoubleBets(BetRequest requestObject) throws SportsbookException {
        BetPlacementResponse betPlacementResponse = new BetPlacementResponse();
        betPlacementResponse.setSumStake(BigDecimal.valueOf(0.));
        betPlacementResponse.setSumPotentialWin(BigDecimal.valueOf(0.));
        
        List <BetResponse> betResponses=new ArrayList <BetResponse>();
        int NumberOfOutcomes=requestObject.getOutcomeOdds().size();
        Set <Long> outcomeIds=requestObject.getOutcomeOdds().keySet();
        HashSet <Long> marketIds = new HashSet <Long>();
        
        Bet currentBet;
    	Long currentId;
    	Iterator <Long> iterator = outcomeIds.iterator();
    	List <Long> currentIds;
    	
    	
    	ArrayList <ArrayList<Long>> combinations;
    	ArrayList <Long> idList = new ArrayList<Long>();

    	for(int i=0;i<NumberOfOutcomes;i++) {
    		currentId=iterator.next();
    		idList.add(currentId);
    		
    		try {
    			marketIds.add(outcomeRepository.findById(currentId).getMarket().getId());
    		}catch(Exception e) {
    			SportsbookException exception = new SportsbookException("Failed to place Double Bet due to bad OutcomeIDs.");
        		throw exception;
    		}
    	}
    	
    	/*Exception*/
    	if(marketIds.size()!=outcomeIds.size()) throw  new SportsbookException("Failed to place Double Bet due to multiple Outcomes of the same Market.");
    	
    	
    	combinations=getCombinations(idList);
    	

    	
    	for(int i=0;i<combinations.size();i++) {
    			
    			currentBet=createBetEntry(requestObject);
    			
    			try {
                	betRepository.save(currentBet);
                	
                	betoutcomemapRepository.save(createBetOutcomeMapEntry(combinations.get(i).get(0),requestObject,currentBet));
    			
                	betoutcomemapRepository.save(createBetOutcomeMapEntry(combinations.get(i).get(1),requestObject,currentBet));
                	
                	currentIds = new ArrayList <Long>();
                	currentIds.add(combinations.get(i).get(0));
                	currentIds.add(combinations.get(i).get(1));
    			
                	transactionRepository.save(createTransactionEntry(requestObject,currentBet,currentIds));

                	betResponses.add(BetResponseMapper.mapToResponse(currentBet, betRepository, 
                			transactionRepository, bettypeRepository, 
                			betoutcomemapRepository, outcomeRepository, 
                			marketRepository, eventRepository));
                	betPlacementResponse.setSumStake(betPlacementResponse.getSumStake().add(requestObject.getBetStake()));
                	betPlacementResponse.setSumPotentialWin(betPlacementResponse.getSumPotentialWin().add(
                			betResponses.get(betResponses.size()-1).getPotentialWin()));
            	
            	}catch(SportsbookException e) {
            		throw e;
            	}catch(Exception e) {
            		SportsbookException exception = new SportsbookException("Failed to place Double Bet.");
            		throw exception;
            	}     		
    	}
    	
    	betPlacementResponse.setListOfBetResponses(betResponses);
    	
  		return betPlacementResponse;	
    }
    
    @Transactional  
    private BetPlacementResponse placeTrebleBets(BetRequest requestObject) throws SportsbookException {
        BetPlacementResponse betPlacementResponse = new BetPlacementResponse();
        betPlacementResponse.setSumStake(BigDecimal.valueOf(0.));
        betPlacementResponse.setSumPotentialWin(BigDecimal.valueOf(0.));
        
        List <BetResponse> betResponses=new ArrayList <BetResponse>();
        int NumberOfOutcomes=requestObject.getOutcomeOdds().size();
        Set <Long> outcomeIds=requestObject.getOutcomeOdds().keySet(); 
    	Long currentId;
    	Iterator <Long> iterator=outcomeIds.iterator();
    	Bet currentBet;
    	List <Long> outcomeIdList = new ArrayList <Long>();
    	
    	
    	
    	if(NumberOfOutcomes!=3) {
    		throw new SportsbookException("Treble Bet placement failed. There can only be exactly 3 Outcomes.");
    		
    	}
    	else {
    		/*Check, so that you can't bet on multiple outcomes of the same market.*/
    		Set <Long> marketIds=new HashSet <Long>();
    		
    		try {
    			for(int i=0;i<NumberOfOutcomes;i++) {
    				currentId=iterator.next();
    				if(marketIds.contains(outcomeRepository.findById(currentId).getMarket().getId())){
    					throw new SportsbookException("Failed to place Treble Bet due multiple Outcomes of the same Market.");
    				}
    				else if(outcomeRepository.findById(currentId) == null) {
    					/*Doesn't work.*/
    					throw new SportsbookException("Failed to place Treble Bet due to bad OutcomeIDs.");
    				}
    				else {
    					marketIds.add(outcomeRepository.findById(currentId).getMarket().getId());
    				}
    			}
    		}catch(SportsbookException e) {
    			throw e;
    		}
    		
        	
    		iterator=outcomeIds.iterator();
    		
    		currentBet=createBetEntry(requestObject);
    		
    		try {
    			betRepository.save(currentBet);
                for(int i=0;i<NumberOfOutcomes;i++) {
                	currentId=iterator.next();
                	
                    betoutcomemapRepository.save(createBetOutcomeMapEntry(currentId,requestObject,currentBet));
 
                	outcomeIdList.add(currentId);
                } 	
            	transactionRepository.save(createTransactionEntry(requestObject,currentBet,outcomeIdList));
            	
            	betResponses.add(BetResponseMapper.mapToResponse(currentBet, betRepository, 
            			transactionRepository, bettypeRepository, 
            			betoutcomemapRepository, outcomeRepository, 
            			marketRepository, eventRepository));
            	betPlacementResponse.setSumStake(betPlacementResponse.getSumStake().add(requestObject.getBetStake()));
            	betPlacementResponse.setSumPotentialWin(betPlacementResponse.getSumPotentialWin().add(
            			betResponses.get(betResponses.size()-1).getPotentialWin()));
                
                betPlacementResponse.setListOfBetResponses(betResponses);
    		}
    		catch(SportsbookException e) {
        		throw e;
        	}
    		catch(Exception e) {
    			SportsbookException exception = new SportsbookException("Failed to place Treble Bet.");
        		throw exception;
    		}	
    		return betPlacementResponse;
    	}
    }
    
    private Bet createBetEntry(BetRequest requestObject) throws SportsbookException {
    	Bet bet=new Bet();

    	bet.setAccountId(accountRepository.findById(requestObject.getAccountId()));
    	bet.setBetTypeId(bettypeRepository.findById(requestObject.getBettypeId()));
    	
    	if(bet.getAccountId()==null || bet.getBetTypeId()==null)
    		throw new SportsbookException("Failed to place Bet due to bad AccountID.");
    	

    	return bet;
    }
    
    private BetOutcomeMap createBetOutcomeMapEntry(Long currentId, BetRequest requestObject, Bet currentBet) throws SportsbookException {
    	
    
    	BetOutcomeMap map = new BetOutcomeMap();
    	map.setBetId(currentBet);
    	map.setOutcomeID(outcomeRepository.findById(currentId));
    	map.setOdds(requestObject.getOutcomeOdds().get(currentId));
    	
    	if(map.getBetId()==null || map.getOutcomeID()==null || map.getOdds()==null)
    		throw new SportsbookException("Failed to place Treble Bet due to bad OutcomeIDs.");  
    		
        return map;
    }

    private Transaction createTransactionEntry(BetRequest requestObject, Bet currentBet,List <Long> outcomeIds) throws SportsbookException {
    	try{
    		Transaction transaction = new Transaction();
    		transaction.setAccount(accountRepository.findById(requestObject.getAccountId()));
    		
    		/*Check account balance*/
    		transaction.setAmount(requestObject.getBetStake().negate());
    		transaction.setBet(currentBet);      
    		transactionDescriptionBuilder(requestObject,outcomeIds,transaction);
    		transaction.setType(TransactionTypes.BET);
    		transaction.setCreatedDate(OffsetDateTime.now());

    		return transaction;
    	}catch(Exception e) {
    		SportsbookException exception = new SportsbookException("Failed to place Bet due to insufficient account balance.");
    		throw exception;
    	}
    }

    private void transactionDescriptionBuilder(BetRequest requestObject, List <Long> outcomeIds, Transaction transaction) {
    	transaction.setDescription(bettypeRepository.findById(requestObject.getBettypeId()).getDescription().getValue()+" Bet ");
    	
    	for(Long currentId : outcomeIds) {
    		transaction.setDescription(transaction.getDescription()
            		+outcomeRepository.findById(currentId).getDescription()+ " @ "+
        			outcomeRepository.findById(currentId).getOdds() +" ");
    	}
    }
}
