package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.ResultTypeRepository;

import com.tricast.repositories.entities.ResultType;

@Service
public class ResultTypeManagerImpl implements ResultTypeManager{
	
	private ResultTypeRepository resultTypeRepository;

	@Inject
	public ResultTypeManagerImpl(ResultTypeRepository resultTypeRepository) {
		this.resultTypeRepository = resultTypeRepository;
	}
	@Override
	public List<ResultType> findAll() {
		return resultTypeRepository.findAll();
	}

	@Override
	public ResultType findById(Long id) {
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

}