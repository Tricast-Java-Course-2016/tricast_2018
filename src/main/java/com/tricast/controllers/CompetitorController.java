package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.CompetitorRequest;
import com.tricast.controllers.responses.CompetitorResponse;
import com.tricast.managers.CompetitorManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(path = "api/competitors")
public class CompetitorController {

	@Autowired
	private CompetitorManager competitorManager;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@GetMapping(path="/{id}")
    public CompetitorResponse findById(@PathVariable("id") long id) {
        return competitorManager.findById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@GetMapping(path="/list/{search}")
    public List<CompetitorResponse> findAll(@PathVariable("search") String search) {
        return competitorManager.search(search);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@GetMapping(path="/list")
    public List<CompetitorResponse> findAll() {
		return competitorManager.findAll();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@PostMapping
	public CompetitorResponse create(CompetitorRequest competitorRequest) {
		return competitorManager.create(competitorRequest);
	}

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@PutMapping(path="/{id}")
	public CompetitorResponse update(long id, CompetitorRequest competitorRequest) {
		return null;
	}

	@DeleteMapping(path="/{id}")
	public void delete(long id) {

	}
}
