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

	public static final int ID_WDW = 1;
	public static final int ID_OUTRIGHT = 9;

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
	private MarketType marketType;

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

	public void setEventId(Event event) {
		this.event = event;
	}

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
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