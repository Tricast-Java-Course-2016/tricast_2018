package com.tricast.controllers.requests;

import java.io.Serializable;

import com.tricast.repositories.entities.EventStatusTypes;

public class EventStatusRequest implements Serializable {

	private static final long serialVersionUID = -2012590825807778419L;

	private EventStatusTypes status;

	public EventStatusTypes getStatus() {
		return status;
	}

	public void setStatus(EventStatusTypes status) {
		this.status = status;
	}
}
