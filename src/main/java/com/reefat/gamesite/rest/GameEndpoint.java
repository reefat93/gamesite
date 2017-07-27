package com.reefat.gamesite.rest;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.reefat.gamesite.service.GameService;

@ApplicationPath("/*")
@Path("/game")
public class GameEndpoint {

	@Inject
	private GameService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllMovies() {
		return service.getAllGames();
	}
	
}
