package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BETS")
public class Bet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3776724655612789427L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "bettypeid")
    private BetType betTypeId;

	@ManyToOne
    @JoinColumn(name = "accountid")
    private Account accountId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BetType getBetTypeId() {
		return betTypeId;
	}

	public void setBetTypeId(BetType betTypeId) {
		this.betTypeId = betTypeId;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}
    
}	