package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.EventTypeRepository;
import com.tricast.repositories.entities.EventType;

@Service
public class EventTypeManagerImpl implements EventTypeManager {

    private EventTypeRepository eventtypeRepository;

    @Inject
    public EventTypeManagerImpl(EventTypeRepository eventtypeRepository) {
        this.eventtypeRepository = eventtypeRepository;
    }

    @Override
    public List<EventType> findAll() {
        return eventtypeRepository.findAll();
    }

    @Override
    public EventType findById(Long id) {
        return eventtypeRepository.findById(id);
    }

    @Override
    public EventType create(EventType eventtype) {
        return eventtypeRepository.save(eventtype);
    }

    @Override
    public EventType update(EventType eventtype) {
        return eventtypeRepository.save(eventtype);
    }

    @Override
    public void deleteById(Long id) {
    	eventtypeRepository.delete(id);
    }

}