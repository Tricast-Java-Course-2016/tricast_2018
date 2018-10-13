package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BETS")
public class Bets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3776724655612789427L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "bettypeid")
    private Long betTypeId;

    @Column(name = "accountid")
    private Long accountId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBetTypeId() {
		return betTypeId;
	}

	public void setBetTypeId(Long betTypeId) {
		this.betTypeId = betTypeId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
    
}	