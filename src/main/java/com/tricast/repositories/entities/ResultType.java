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
@Table(name = "RESULTTYPES")

public class ResultType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1949046511344617510L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "description")
    private ResultTypes description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ResultTypes getDescription() {
		return description;
	}

	public void setDescription(ResultTypes description) {
		this.description = description;
	}
    
    
}
