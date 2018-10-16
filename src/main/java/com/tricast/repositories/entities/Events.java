package com.tricast.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS")
public class Events implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7208235612252822959L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "eventtypeid")
    private EventTypes eventTypeId;
	
	@ManyToOne
	@JoinColumn(name="leagueid")
	private League leagueId;
	
	@Column(name="description")
	private String description;

	@Column(name="status")
	private String status;
	
	@Column(name="starttime")
	private Date startTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventTypes getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(EventTypes eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public League getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(League leagueId) {
		this.leagueId = leagueId;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
}