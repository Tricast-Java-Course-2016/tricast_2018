package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tricast.repositories.entities.converters.PeriodTypesConverter;

@Entity
@Table(name = "PERIODTYPES")
public class PeriodType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7416880906917279709L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "description")
    @Convert(converter = PeriodTypesConverter.class)
    private PeriodTypes description;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PeriodTypes getDescription() {
		return this.description;
	}

	public void setDescription(PeriodTypes description){
		this.description=description;
	}
}	