package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.PeriodTypesRepository;
import com.tricast.repositories.entities.PeriodTypes;

@Service
public class PeriodTypesManagerImpl implements PeriodTypesManager {

    private PeriodTypesRepository periodtypesRepository;

    @Inject
    public PeriodTypesManagerImpl(PeriodTypesRepository periodtypesRepository) {
        this.periodtypesRepository = periodtypesRepository;
    }

    @Override
    public List<PeriodTypes> findAll() {
        return periodtypesRepository.findAll();
    }

    @Override
    public PeriodTypes findById(Long id) {
        return periodtypesRepository.findById(id);
    }

    @Override
    public PeriodTypes create(PeriodTypes periodtype) {
        return periodtypesRepository.save(periodtype);
    }

    @Override
    public PeriodTypes update(PeriodTypes periodtype) {
        return periodtypesRepository.save(periodtype);
    }

    @Override
    public void deleteById(Long id) {
    	periodtypesRepository.delete(id);
    }

}
