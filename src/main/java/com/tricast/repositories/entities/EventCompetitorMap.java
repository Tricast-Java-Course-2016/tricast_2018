package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTCOMPETITORMAP")
public class EventCompetitorMap implements Serializable { 
	
	private static final long serialVersionUID = -104291198253556317L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name = "competitorid")
	private long competitorId;
	
	@ManyToOne
	@JoinColumn(name = "eventid")
	private Event event;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(long competitorId) {
		this.competitorId = competitorId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
