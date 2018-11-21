package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.EventRepository;
import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.PeriodType;
import com.tricast.repositories.entities.PeriodTypeEnum;
import com.tricast.repositories.entities.SportEnum;

@Service
public class PeriodTypeManagerImpl implements PeriodTypeManager {

    private PeriodTypeRepository periodtypeRepository;
    private EventRepository eventRepository;

    @Inject
    public PeriodTypeManagerImpl(PeriodTypeRepository periodtypeRepository,
    							 EventRepository eventRepository){
    	
        this.periodtypeRepository = periodtypeRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<PeriodType> findAll() {
        return periodtypeRepository.findAll();
    }

    @Override
    public PeriodType findById(Long id) {
        return periodtypeRepository.findById(id);
    }

    @Override
    public PeriodType create(PeriodType periodtype) {
        return periodtypeRepository.save(periodtype);
    }

    @Override
    public PeriodType update(PeriodType periodtype) {
        return periodtypeRepository.save(periodtype);
    }

    @Override
    public void deleteById(Long id) {
    	periodtypeRepository.delete(id);
    }

	@Override
	public List<PeriodTypeEnum> findByEventId(long eventId) {
		Event event = eventRepository.findById(eventId);
		
		List<PeriodTypeEnum> periods = new ArrayList<PeriodTypeEnum>();
		
		if(event.getEventType().getId() == 1) {
			periods = SportEnum.FOOTBALL.getPeriodTpes();
		} else {
			periods = SportEnum.HORSERACING.getPeriodTpes();
		}
		return periods;
	}

}
