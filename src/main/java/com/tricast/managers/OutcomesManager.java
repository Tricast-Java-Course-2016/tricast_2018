package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Outcomes;

public interface OutcomesManager {

    List<Outcomes> findAll();

    Outcomes findById(Long id);

    Outcomes create(Outcomes outcome);

    Outcomes update(Outcomes outcome);

    void deleteById(Long id);

}
