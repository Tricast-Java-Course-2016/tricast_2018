package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BETTYPES")
public class BetTypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5502914355781114567L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "description")
    private BetType description;
    
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BetType getDescription() {
		return description;
	}

	public void setDescription(BetType description) {
		this.description = description;
	}
	
}
