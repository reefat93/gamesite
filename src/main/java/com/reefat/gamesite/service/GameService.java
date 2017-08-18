package com.reefat.gamesite.service;

public interface GameService {
	
	String getAllGames();
	
	String getAllGamesComplete();

	String createGame(String gameJSON);
	
	String updateGame(String gameJSON);
	
	String deleteGame(int game_id);
	
}
