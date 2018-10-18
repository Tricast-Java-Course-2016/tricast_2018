package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tricast.repositories.entities.converters.MarketTypesConverter;

@Entity
@Table(name = "MARKETTYPES")
public class MarketType implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7227671871339213573L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "description")
    @Convert(converter = MarketTypesConverter.class)
    private MarketTypes description;
    
	@ManyToOne
    @JoinColumn(name = "sportid")
    private Sport sportId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MarketTypes getDescription() {
		return this.description;
	}

	public void setDescription(MarketTypes description){
		this.description=description;
	}

	public Sport getSportId() {
		return sportId;
	}

	public void setSportId(Sport sportId) {
		this.sportId = sportId;
	}
	
}	