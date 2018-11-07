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
@Table(name = "OUTCOMES")
public class Outcome implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5462775005542276847L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "marketid")
    private Market market;
	
	@Column(name="outcomecode")
	private String outcomeCode;
	
	@Column(name="description")
	private String description;
	
	@Column(name="odds")
	private double odds;
	
	@Column(name="winyn",nullable=true)
	private Integer winYN;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public String getOutcomeCode() {
		return outcomeCode;
	}

	public void setOutcomeCode(String outcomeCode) {
		this.outcomeCode = outcomeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getOdds() {
		return odds;
	}

	public void setOdds(double odds) {
		this.odds = odds;
	}

	public Integer getWinYN() {
		return winYN;
	}

	public void setWinYN(Integer winYN) {
			this.winYN = winYN;
	}
	
}