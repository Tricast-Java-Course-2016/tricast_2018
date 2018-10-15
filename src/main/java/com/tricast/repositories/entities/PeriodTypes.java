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
@Table(name = "PERIODTYPES")
public class PeriodTypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7416880906917279709L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "description")
    private String description;

    private PeriodType getPeriodType() {
    	for(int i=0;i<PeriodType.values().length;i++) {
    		if(PeriodType.values()[i].getValue().equals(description)) return PeriodType.values()[i];
    	}
    	return null;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PeriodType getDescription() {
		return getPeriodType();
	}

	public boolean setDescription(String description) {
	    for(int i=0;i<PeriodType.values().length;i++) {
	    	if(PeriodType.values()[i].getValue().equals(description)) {
	    		this.description = description;
	    		return true;
	    	}
	    }
	    return false;
	}
    
}	