package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.EventRepository;
import com.tricast.repositories.entities.Event;

@Service
public class EventManagerImpl implements EventManager {

    private EventRepository EventRepository;

    @Inject
    public EventManagerImpl(EventRepository eventRepository) {
        this.EventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return EventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return EventRepository.findById(id);
    }

    @Override
    public Event create(Event event) {
        return EventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return EventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        EventRepository.delete(id);
    }

}