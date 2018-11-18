package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESULTS")
public class Result implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1917253139349204377L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "resulttypeid")
	private ResultType resultType;
	
	@Column(name = "result")
	private Integer result;

	@ManyToOne
	@JoinColumn(name = "periodtypeid")
	private PeriodType periodType;
	
	@ManyToOne
	@JoinColumn(name = "eventcompetitormapid")
	private EventCompetitorMap eventCompetitorMap;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public PeriodType getPeriodType() {
		return periodType;
	}

	public void setPeriodType(PeriodType periodType) {
		this.periodType = periodType;
	}

	public EventCompetitorMap getEventCompetitorMap() {
		return eventCompetitorMap;
	}

	public void setEventCompetitorMap(EventCompetitorMap eventCompetitorMap) {
		this.eventCompetitorMap = eventCompetitorMap;
	}
}
