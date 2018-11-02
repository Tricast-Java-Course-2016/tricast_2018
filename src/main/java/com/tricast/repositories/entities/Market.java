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
public class Market implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4266469379148385522L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
	
	@ManyToOne
	@JoinColumn(name="markettypeid")
	private MarketType marketTypeId;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="periodtypeid")
	private PeriodType periodTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public MarketType getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(MarketType marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PeriodType getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(PeriodType periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
		
}