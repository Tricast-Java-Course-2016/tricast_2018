package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BETOUTCOMEMAP")
public class BetOutcomeMap implements Serializable {

	private static final long serialVersionUID = -8197525315827453553L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
	@JoinColumn(name = "betid")
    private Bets bet;
	
	@OneToOne
	@JoinColumn(name = "outcomeid")
    private Outcomes outcomeID;

    @Column(name = "odds")
    private Double odds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bets getBetId() {
		return bet;
	}

	public void setBetId(Bets bet) {
		this.bet = bet;
	}

	public Outcomes getOutcomeID() {
		return outcomeID;
	}

	public void setOutcomeID(Outcomes outcomeID) {
		this.outcomeID = outcomeID;
	}

	public Double getOdds() {
		return odds;
	}

	public void setOdds(Double odds) {
		this.odds = odds;
	}


}
