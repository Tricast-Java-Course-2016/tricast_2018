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

	private static final long serialVersionUID = 7227671871339213573L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

	@ManyToOne
    @JoinColumn(name = "sportid")
    private Sport sportId;

    @Column(name = "description", insertable = false, updatable = false)
    @Convert(converter = MarketTypesConverter.class)
    private MarketTypeEnum type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sport getSportId() {
		return sportId;
	}

	public void setSportId(Sport sportId) {
		this.sportId = sportId;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MarketTypeEnum getType() {
        return type;
    }

    public void setType(MarketTypeEnum type) {
        this.type = type;
    }
}