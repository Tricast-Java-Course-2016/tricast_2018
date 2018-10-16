package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.EventTypesRepository;
import com.tricast.repositories.entities.EventTypes;

@Service
public class EventTypesManagerImpl implements EventTypesManager {

    private EventTypesRepository eventtypesRepository;

    @Inject
    public EventTypesManagerImpl(EventTypesRepository eventtypesRepository) {
        this.eventtypesRepository = eventtypesRepository;
    }

    @Override
    public List<EventTypes> findAll() {
        return eventtypesRepository.findAll();
    }

    @Override
    public EventTypes findById(Long id) {
        return eventtypesRepository.findById(id);
    }

    @Override
    public EventTypes create(EventTypes eventtype) {
        return eventtypesRepository.save(eventtype);
    }

    @Override
    public EventTypes update(EventTypes eventtype) {
        return eventtypesRepository.save(eventtype);
    }

    @Override
    public void deleteById(Long id) {
    	eventtypesRepository.delete(id);
    }

}