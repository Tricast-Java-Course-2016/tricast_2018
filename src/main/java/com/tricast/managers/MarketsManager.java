package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Markets;

public interface MarketsManager {

    List<Markets> findAll();

    Markets findById(Long id);

    Markets create(Markets market);

    Markets update(Markets market);

    void deleteById(Long id);

}
