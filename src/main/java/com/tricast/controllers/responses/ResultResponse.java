package com.tricast.controllers.responses;

import java.util.List;

import com.tricast.repositories.entities.Result;

public class ResultResponse {
    private Long id;
    private String description;
    private List<Result> results;

    public ResultResponse(Long id, String description){
        this.description=description;
        this.id=id;
    }

    public void addResult(Result resultCreateResponse){
        results.add(resultCreateResponse);
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Result> getResults() {
        return results;
    }
}
