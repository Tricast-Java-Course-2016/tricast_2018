package com.tricast.managers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.EventRepository;
import com.tricast.repositories.ResultTypeRepository;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.EventTypeEnum;
import com.tricast.repositories.entities.ResultType;
import com.tricast.repositories.entities.ResultTypeEnum;
import com.tricast.repositories.entities.SportEnum;

@Service
public class ResultTypeManagerImpl implements ResultTypeManager{
	
	private ResultTypeRepository resultTypeRepository;
	private EventRepository eventRepository;

	@Inject
	public ResultTypeManagerImpl(ResultTypeRepository resultTypeRepository,
								 EventRepository eventRepository) {
		this.resultTypeRepository = resultTypeRepository;
        this.eventRepository = eventRepository;
	}
	@Override
	public List<ResultType> findAll() {
		return resultTypeRepository.findAll();
	}

	@Override
	public ResultType findById(int id) {
		return resultTypeRepository.findById(id);
	}

	@Override
	public ResultType create(ResultType resulttype) {
		return resultTypeRepository.save(resulttype);
	}

	@Override
	public ResultType update(ResultType resulttype) {
		return resultTypeRepository.save(resulttype);
	}

	@Override
	public void deleteById(Long id) {
		resultTypeRepository.delete(id);
		
	}
	@Override
	public List<ResultTypeEnum> findByEventId(long eventId) {
		Event event = eventRepository.findById(eventId);

		List<ResultTypeEnum> results = new ArrayList<ResultTypeEnum>();

		if(event.getEventType().getType() == EventTypeEnum.GAMEEVENT) {
			results = SportEnum.FOOTBALL.getResultTypes();
		} else {
			results = SportEnum.HORSERACING.getResultTypes();
		}
		
		return results;
	}

}