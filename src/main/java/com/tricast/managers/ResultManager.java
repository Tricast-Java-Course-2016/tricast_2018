package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Result;

public interface ResultManager {
	
    List<Result> findAll();

    Result findById(Long id);

    Result create(Result result);

    Result update(Result result);

    void deleteById(Long id);
    
}
