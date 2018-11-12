package com.tricast.managers;

import java.util.List;

import com.tricast.controllers.requests.BetRequest;
import com.tricast.controllers.responses.BetPlacementResponse;
import com.tricast.controllers.responses.BetResponse;
import com.tricast.managers.exceptions.SportsbookException;

public interface BetManager {

    List<BetResponse> findAll();

    BetResponse findById(Long id);
    
    List<BetResponse> findByEventId(long id);

    BetPlacementResponse create(BetRequest requestObject)  throws SportsbookException;


}
