package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPORTS")
public class Sports implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7912760514213968427L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "description")
    @Convert(converter = SportsConverter.class)
    private Sport description;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sport getDescription() {
		return this.description;
	}

	public void setDescription(Sport description){
		this.description=description;
	}
}	