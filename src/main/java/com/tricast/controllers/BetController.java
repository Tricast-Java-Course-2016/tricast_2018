package com.tricast.controllers;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.BetRequest;
import com.tricast.controllers.responses.BetPlacementResponse;
import com.tricast.controllers.responses.BetResponse;
import com.tricast.managers.BetManager;
import com.tricast.managers.exceptions.OddsException;
import com.tricast.managers.exceptions.SportsbookException;

@RestController
@RequestMapping(path = "api/bets")
public class BetController {

	private static final Logger log = LogManager.getLogger(BetController.class);

    @Autowired
    private BetManager betManager;
    
    @GetMapping
    public List<BetResponse> findAll() {
        return betManager.findAll();
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
			BetResponse bet = betManager.findById(id);
			return ResponseEntity.ok(bet);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }

	@PostMapping
	public ResponseEntity<?> createBet(@RequestBody BetRequest newBet) {

		log.trace("Trying to create bet for: AccountId: " + newBet.getAccountId());

		try {
			BetPlacementResponse bet = betManager.create(newBet,true);
			return ResponseEntity.ok(bet);
		}catch(OddsException oe) {
			try {
				BetPlacementResponse bet = betManager.getNewOdds(newBet);
				return ResponseEntity.ok(bet);
			} catch (SportsbookException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(path="/withoutOddsCheck")
	public ResponseEntity<?> createBetWithoutOddsCheck(@RequestBody BetRequest newBet) {

		log.trace("Trying to create bet for: AccountId: " + newBet.getAccountId());

		try {
			BetPlacementResponse bet = betManager.create(newBet,false);
			return ResponseEntity.ok(bet);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path="eventid/{id}")
	public List<BetResponse> findByEventId(@PathVariable("id") long id) {
		return betManager.findByEventId(id);
	}
}
