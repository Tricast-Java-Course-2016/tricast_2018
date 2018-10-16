package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.EventsRepository;
import com.tricast.repositories.entities.Events;

@Service
public class EventsManagerImpl implements EventsManager {

    private EventsRepository EventsRepository;

    @Inject
    public EventsManagerImpl(EventsRepository eventRepository) {
        this.EventsRepository = eventRepository;
    }

    @Override
    public List<Events> findAll() {
        return EventsRepository.findAll();
    }

    @Override
    public Events findById(Long id) {
        return EventsRepository.findById(id);
    }

    @Override
    public Events create(Events event) {
        return EventsRepository.save(event);
    }

    @Override
    public Events update(Events event) {
        return EventsRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        EventsRepository.delete(id);
    }

}