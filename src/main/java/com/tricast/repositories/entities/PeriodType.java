package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tricast.repositories.entities.converters.PeriodTypesConverter;

@Entity
@Table(name = "PERIODTYPES")
public class PeriodType implements Serializable {

    private static final long serialVersionUID = 7416880906917279709L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "description", insertable = false, updatable = false)
    @Convert(converter = PeriodTypesConverter.class)
    private PeriodTypeEnum type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PeriodTypeEnum getType() {
        return type;
    }

    public void setType(PeriodTypeEnum type) {
        this.type = type;
    }
}