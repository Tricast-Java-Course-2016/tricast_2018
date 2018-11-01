package com.tricast.managers;

import java.util.List;

import com.tricast.controllers.requests.BetRequest;
import com.tricast.controllers.responses.BetResponse;

public interface BetManager {

    List<BetResponse> findAll();

    BetResponse findById(Long id);

    //BetResponse create(BetRequest newBet);


}
