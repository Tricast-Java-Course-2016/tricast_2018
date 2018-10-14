package com.tricast.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.repositories.ResultRepository;
import com.tricast.repositories.entities.Result;

@Service
public class ResultManagerImpl implements ResultManager {

    @Autowired
    private ResultRepository resultRepository;
	
	@Override
	public List<Result> findAll() {
		return resultRepository.findAll();
	}

	@Override
	public Result findById(Long id) {
        return resultRepository.findOne(id);
	}

	@Override
	public Result create(Result result) {
		return resultRepository.save(result);
	}

	@Override
	public Result update(Result result) {
		return resultRepository.save(result);
	}

	@Override
	public void deleteById(Long id) {
		resultRepository.delete(id);
	}
	
	
	
}
