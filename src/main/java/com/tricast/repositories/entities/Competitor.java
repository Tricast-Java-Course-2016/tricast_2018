package com.tricast.repositories.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "competitors")
public class Competitor implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9085863544341717808L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "description")
    private String description;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany(mappedBy="competitors", fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
	@JsonIgnoreProperties("competitors")
    public Set<League> leagues = new HashSet<>();

	public Set<League> getLeagues() {
		return leagues;
	}
    
}
