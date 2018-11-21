package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tricast.repositories.entities.converters.SportsConverter;

@Entity
@Table(name = "SPORTS")
public class Sport implements Serializable {

	private static final long serialVersionUID = -7912760514213968427L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "description", insertable = false, updatable = false)
    @Convert(converter = SportsConverter.class)
    private SportEnum type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public SportEnum getType() {
		return type;
	}
}