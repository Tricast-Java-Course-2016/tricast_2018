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
	private ResultType resultTypeId;
	
	@Column(name = "result")
	private Integer result;

	@ManyToOne
	@JoinColumn(name = "periodtypeid")
	private PeriodType periodTypeId;
	
	@Column(name = "eventcompetitormapid")
	private Long eventCompetitorMapId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ResultType getResultTypeId() {
		return resultTypeId;
	}

	public void setResultTypeId(ResultType resultTypeId) {
		this.resultTypeId = resultTypeId;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public PeriodType getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(PeriodType periodTypeId) {
		this.periodTypeId = periodTypeId;
	}

	public Long getEventCompetitorMapId() {
		return eventCompetitorMapId;
	}

	public void setEventCompetitorMapId(Long eventCompetitorMapId) {
		this.eventCompetitorMapId = eventCompetitorMapId;
	}
	
	
}
