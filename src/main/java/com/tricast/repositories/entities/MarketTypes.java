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

import com.tricast.repositories.entities.converters.MarketTypeConverter;

@Entity
@Table(name = "MARKETTYPES")
public class MarketTypes implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7227671871339213573L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "description")
    @Convert(converter = MarketTypeConverter.class)
    private MarketType description;
    
	@ManyToOne
    @JoinColumn(name = "sportid")
    private Sports sportId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MarketType getDescription() {
		return this.description;
	}

	public void setDescription(MarketType description){
		this.description=description;
	}

	public Sports getSportId() {
		return sportId;
	}

	public void setSportId(Sports sportId) {
		this.sportId = sportId;
	}
	
}	