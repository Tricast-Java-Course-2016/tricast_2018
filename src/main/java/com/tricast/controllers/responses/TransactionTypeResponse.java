package com.tricast.controllers.responses;

import java.io.Serializable;

public class TransactionTypeResponse implements Serializable {

    private static final long serialVersionUID = 2181267123541222485L;

    private long id;
    private String type;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
