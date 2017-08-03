package com.reefat.gamesite.service;

public interface GameService {
	
	String getAllGames();
	
	String getAllGamesComplete();

	String createGame(String gameJSON);
	
}
