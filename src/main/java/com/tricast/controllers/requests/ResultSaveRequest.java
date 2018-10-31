package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.List;

public class ResultSaveRequest  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1325922506875680157L;

    private long eventId;
    private List<ResultRequest> resultToSave;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public List<ResultRequest> getResultToSave() {
        return resultToSave;
    }

    public void setResultToSave(List<ResultRequest> resultToSave) {
        this.resultToSave = resultToSave;
    }


}
