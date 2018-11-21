package com.tricast.repositories.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tricast.repositories.entities.converters.PeriodTypesConverter;
import com.tricast.repositories.entities.converters.ResultTypesConverter;

@Entity 
@Table(name = "RESULTTYPES")

public class ResultType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1949046511344617510L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "description")
    private String description;
    
    @Column(name = "description", insertable = false, updatable = false)
    @Convert(converter = ResultTypesConverter.class)
    private ResultTypeEnum type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ResultTypeEnum getType() {
		return type;
	}

	public void setType(ResultTypeEnum type) {
		this.type = type;
	}
}
