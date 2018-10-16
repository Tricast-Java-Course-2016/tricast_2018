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
@Table(name = "MARKETS")
public class Markets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4266469379148385522L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "eventid")
    private Events eventId;
	
	@ManyToOne
	@JoinColumn(name="markettypeid")
	private MarketTypes marketTypeId;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="periodtypeid")
	private PeriodTypes periodTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Events getEventId() {
		return eventId;
	}

	public void setEventId(Events eventId) {
		this.eventId = eventId;
	}

	public MarketTypes getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(MarketTypes marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PeriodTypes getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(PeriodTypes periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
		
}