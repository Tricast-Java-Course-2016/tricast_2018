package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.PeriodTypeRepository;
import com.tricast.repositories.entities.PeriodType;

@Service
public class PeriodTypeManagerImpl implements PeriodTypeManager {

    private PeriodTypeRepository periodtypeRepository;

    @Inject
    public PeriodTypeManagerImpl(PeriodTypeRepository periodtypeRepository) {
        this.periodtypeRepository = periodtypeRepository;
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

}
