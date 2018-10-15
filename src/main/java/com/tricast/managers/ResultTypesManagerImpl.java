package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.ResultTypesRepository;

import com.tricast.repositories.entities.ResultTypes;

@Service
public class ResultTypesManagerImpl implements ResultTypesManager{
	
	private ResultTypesRepository resultTypesRepository;

	@Inject
	public ResultTypesManagerImpl(ResultTypesRepository resultTypesRepository) {
		this.resultTypesRepository = resultTypesRepository;
	}
	@Override
	public List<ResultTypes> findAll() {
		return resultTypesRepository.findAll();
	}

	@Override
	public ResultTypes findById(Long id) {
		return resultTypesRepository.findById(id);
	}

	@Override
	public ResultTypes create(ResultTypes resulttype) {
		return resultTypesRepository.save(resulttype);
	}

	@Override
	public ResultTypes update(ResultTypes resulttype) {
		return resultTypesRepository.save(resulttype);
	}

	@Override
	public void deleteById(Long id) {
		resultTypesRepository.delete(id);
		
	}

}