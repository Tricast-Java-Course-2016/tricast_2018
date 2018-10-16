package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.MarketTypes;

public interface MarketTypesManager {

    List<MarketTypes> findAll();

    MarketTypes findById(Long id);

    MarketTypes create(MarketTypes markettype);

    MarketTypes update(MarketTypes markettype);

    void deleteById(Long id);

}
