package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.builders.EventDetailResponseBuilder;
import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.repositories.EventRepository;
import com.tricast.repositories.MarketRepository;
import com.tricast.repositories.OutcomeRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.entities.Event;

@Service
public class EventManagerImpl implements EventManager {

    private EventRepository eventRepository;
    private MarketRepository marketRepository;
    private OutcomeRepository outcomeRepository;
    private PeriodTypeRepository periodtypeRepository;

    @Inject
    public EventManagerImpl(EventRepository eventRepository, 
    		MarketRepository marketRepository, 
    		OutcomeRepository outcomeRepository,
    		PeriodTypeRepository periodtypeRepository) {
        this.eventRepository = eventRepository;
        this.marketRepository = marketRepository;
        this.outcomeRepository = outcomeRepository;
        this.periodtypeRepository = periodtypeRepository;
        
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.delete(id);
    }
    
    @Override
    public EventDetailResponse detail(Long id) {
    	return EventDetailResponseBuilder.build(id, eventRepository, marketRepository, outcomeRepository, periodtypeRepository);
    }

}