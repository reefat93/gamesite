package com.reefat.gamesite.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.reefat.gamesite.database.JDBCConnection;
import com.reefat.gamesite.persistence.Game;
import com.reefat.gamesite.util.JSONUtil;

public class DBGameService implements GameService { //GameDAO Implementation with DAO methods
	
	@Inject
	private JSONUtil util; //instantiated to convert java objects to json with method

	@Override //Run method!!
	public String getAllGames() { //Method to get all games (game objects) in Games table 

		List<Game> gameData = new ArrayList<>(); //Creating an empty arraylist of games
		JDBCConnection jdbcConnection = new JDBCConnection(); //Instantiation the JDBCConnection class
		Connection connection = jdbcConnection.getConnnection(); //Setting connection object to the mySQL server connected one
		System.out.println("Connection established.");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM games"); //The SQL STATEMENT WE WOULD LIKE TO PERFORM
			ResultSet rs = ps.executeQuery(); //Executing the prepared statement and storing it as a resultSet object (a set)
	
			while (rs.next()) { //At each stage/data object, do the following with the data, then move to next one unless end of set
				Game game = new Game(); //Create a game object
				game.setGame_id(rs.getInt("game_id")); //Set the details of the game object with setters
				game.setTitle(rs.getString("title")); //Details of data object are retrieved with a getType method for the resultSet
				game.setRelease_date(rs.getString("release_date"));
				game.setDescription(rs.getString("description"));
				game.setImage_url(rs.getString("image_url"));
				game.setAge_rating(rs.getString("age_rating"));
				game.setGenre_id(rs.getInt("genre_id"));
				game.setDeveloper_id(rs.getInt("developer_id"));
				gameData.add(game); //Once attributes of data object are retrieved from database, set for new game object,
									//add this game object to the array of Game objects, gameData. 
				                    //While loop repeats to add all data retrieved from SQL statement to gameData list.
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(connection != null) {
				try { 
					connection.close();
					System.out.println("Connection closed.");
				} catch (SQLException e) {
					e.printStackTrace(); }
			}
		}
		
		return util.getJSONForObject(gameData); //Return the JSON of game objects (converted from Java objects using toJson method)
	}

	@Override
	public String getAllGamesComplete() {
		// TODO Auto-generated method stub
		return null;
	}

}
