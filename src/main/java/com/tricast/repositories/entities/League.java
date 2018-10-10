package com.tricast.repositories.entities;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "LEAGUES")
public class League implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 2318290506163896020L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "sportid")
    private Integer sportId;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "LEAGUECOMPETITORMAP",
        joinColumns = 		 @JoinColumn(name="leagueid", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="competitorid", referencedColumnName="id")
        )
    public Set<Competitor> competitors = new HashSet<>();

    
	public Set<Competitor> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(Set<Competitor> competitors) {
		this.competitors = competitors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}
}
