package com.tricast.builders;

import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.controllers.responses.OutcomeResponse;
import com.tricast.managers.mappers.MarketResponseMapper;
import com.tricast.managers.mappers.OutcomeResponseMapper;
import com.tricast.managers.mappers.PeriodTypeResponseMapper;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.PeriodMarkets;

public class EventDetailResponseBuilder {

    public static EventDetailResponse build(Long id,
    		EventRepository eventRepository,
    		MarketRepository marketRepository,
    		OutcomeRepository outcomeRepository,
    		PeriodTypeRepository periodtypeRepository) {

    	EventDetailResponse responseObject = new EventDetailResponse();
    	List <PeriodMarkets> periodMarketsList= new ArrayList <>();
    	List <Outcome> outcomes;
    	List <OutcomeResponse> outcomeResponses;
    	PeriodMarkets currentPeriodMarkets;

        // AKOS eventRepository.findById(id)
        // a repository hívásokkal célszerű sporólni mivel azok elég erőforrásigényes adatbázis hívások
    	responseObject.setEventDescription(eventRepository.findById(id).getDescription());
    	responseObject.setEventStartTime(eventRepository.findById(id).getStartTime());


        // AKOS nehezen olvasható
        // foreach jobb végigiterálni listákon
        // valahogy így:
        // for (Market market : markets) {
        //
        // }

    	for(int i=0;i<marketRepository.findByEventId(eventRepository.findById(id)).size();i++) {
    		outcomeResponses = new ArrayList <>();
    		currentPeriodMarkets=new PeriodMarkets();

            // AKOS
            // marketRepository.findByEventId
            // ez a nevével ellentétben nem id alapján keres hanem egy teljes event entity alapján
            // így nézne ki id alapján:
            // List<Market> findByEvent_id(Long eventId);
            // (az alulvonás után a marketon levő Event object Id mezője alapján keres)
    		currentPeriodMarkets.setMarket(MarketResponseMapper.mapToResponse(marketRepository.findByEventId(eventRepository.findById(id)).get(i), eventRepository));
    		currentPeriodMarkets.setPeriod(PeriodTypeResponseMapper.build(marketRepository.findByEventId(eventRepository.findById(id)).get(i).getPeriodTypeId()));

    		outcomes=outcomeRepository.findByMarketId(marketRepository.findByEventId(eventRepository.findById(id)).get(i));
    		for(int j=0;j<outcomes.size();j++) {
    				outcomeResponses.add(OutcomeResponseMapper.mapToResponse(outcomes.get(j), outcomeRepository, marketRepository));
    		}

    		currentPeriodMarkets.setOutcomes(outcomeResponses);
    		periodMarketsList.add(currentPeriodMarkets);
    	}

    	responseObject.setPeriodMarketsList(periodMarketsList);


        return responseObject;
    }

}