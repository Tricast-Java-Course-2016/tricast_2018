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
@Table(name = "EVENTTYPES")

public class EventType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3579005461705985865L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "description")   
    private EventTypes description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventTypes getDescription() {
		return description;
	}

	public void setDescription(EventTypes description) {
		this.description = description;
	}
}