package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Market;

public interface MarketManager {

    List<Market> findAll();

    Market findById(Long id);

    Market create(Market market);

    Market update(Market market);

    void deleteById(Long id);

}
