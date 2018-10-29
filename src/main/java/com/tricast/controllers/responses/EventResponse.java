package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.List;

import com.tricast.repositories.entities.Competitor;

public class EventResponse implements Serializable {

	private static final long serialVersionUID = 4538977295592317848L;

	private Long id;
	private String description; 
	private Long leagueId;
	private List<Competitor> competitors;
}
