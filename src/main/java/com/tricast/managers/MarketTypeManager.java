package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.MarketType;

public interface MarketTypeManager {

    List<MarketType> findAll();

    MarketType findById(Long id);

    MarketType create(MarketType markettype);

    MarketType update(MarketType markettype);

    void deleteById(Long id);

}
