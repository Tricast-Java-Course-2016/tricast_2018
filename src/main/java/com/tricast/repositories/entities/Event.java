package com.tricast.repositories.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS")
public class Event implements Serializable {

	private static final long serialVersionUID = 7208235612252822959L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
    @JoinColumn(name = "eventtypeid")
    private EventType eventType;

	@OneToOne
	@JoinColumn(name="leagueid")
	private League league;

	@Column(name="description")
	private String description;

	@Column(name="status")
	private String status;

	@Column(name="starttime")
	private Calendar startTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "EVENTCOMPETITORMAP", joinColumns = @JoinColumn(name = "eventid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "competitorid", referencedColumnName = "id"))
    private List<Competitor> competitors;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

    public List<Competitor> getCompetitors() {
        return competitors;
    }
}