package com.reefat.gamesite.rest;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.reefat.gamesite.service.GameService;

@ApplicationPath("/*")
@Path("/gameAPI")
public class GameEndpoint {

	@Inject
	private GameService service;

	@Path("/game")
	@GET
	@Produces({ "application/json" })
	public String getGameDetails() {
		return service.getAllGames();
	}
	
	@Path("/gamecomplete")
	@GET
	@Produces({ "application/json" })
	public String getGameCompleteDetails() {
		return service.getAllGamesComplete();
	}
	
	@Path("/game")
	@POST
	@Produces({ "application/json" })
	public String postGame(String gameJSON) {
		return service.createGame(gameJSON);
	}
}
