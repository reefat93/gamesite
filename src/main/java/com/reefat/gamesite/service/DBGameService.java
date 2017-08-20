package com.reefat.gamesite.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.reefat.gamesite.database.JDBCConnection;
import com.reefat.gamesite.persistence.Game;
import com.reefat.gamesite.persistence.GameComplete;
import com.reefat.gamesite.util.JSONUtil;

public class DBGameService implements GameService { //GameDAO Implementation with DAO methods
	
	@Inject
	private JSONUtil util; //instantiated to convert java objects to json with method

	@Override
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
			return e.toString();
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
		
		List<GameComplete> gameCompleteData = new ArrayList<>(); 
		JDBCConnection jdbcConnection = new JDBCConnection(); 
		Connection connection = jdbcConnection.getConnnection(); 
		System.out.println("Connection established.");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM games_complete"); 
			ResultSet rs = ps.executeQuery(); 
	
			while (rs.next()) { 
				GameComplete gc = new GameComplete();
				gc.setGame_id(rs.getInt("game_id"));
				gc.setTitle(rs.getString("title"));
				gc.setRelease_date(rs.getString("release_date"));
				gc.setDescription(rs.getString("description"));
				gc.setImage_url(rs.getString("image_url"));
				gc.setAge_rating(rs.getString("age_rating"));
				gc.setGenre_name(rs.getString("genre_name"));
				gc.setDeveloper_name(rs.getString("developer_name"));
				gameCompleteData.add(gc); 
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			return e.toString();
		} finally { 
			if(connection != null) {
				try { 
					connection.close();
					System.out.println("Connection closed.");
				} catch (SQLException e) {
					e.printStackTrace(); }
			}
		}
		
		return util.getJSONForObject(gameCompleteData); 

	}

	@Override
	public String createGame(String gameJSON) {
		
		JDBCConnection jdbcConnection = new JDBCConnection();
		Connection connection = jdbcConnection.getConnnection();
		System.out.println("Connection established.");
		
		try {
			
			Game game = util.getObjectForJSON(gameJSON, Game.class);
			Statement statement = connection.createStatement();
			String sqlQuery = "INSERT INTO games VALUES(" + game.getGame_id() + ",'" + game.getTitle() + "','" + game.getRelease_date() + "','"
			+ game.getDescription() + "','" + game.getImage_url() + "','" + game.getAge_rating() + "'," + game.getDeveloper_id() + ","
			+ game.getGenre_id() + ")"; 
			statement.executeUpdate(sqlQuery); 
			
		} catch (SQLException e) {
			e.printStackTrace();
			return util.getJSONForObject(e);
			
		} finally { 
			if(connection != null) {
				try { 
					connection.close();
					System.out.println("Connection closed.");
				} catch (SQLException e) {
					e.printStackTrace(); }
			}
		}
		
		return "{\"message\": \"Game successfully added to database\"}"; 
		
	}

	@Override
	public String updateGame(String gameJSON) {
		
		List<Integer> gameIds = new ArrayList<>(); 
		int updatedGameId;
		JDBCConnection jdbcConnection = new JDBCConnection();
		Connection connection = jdbcConnection.getConnnection();
		System.out.println("Connection established.");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT game_id FROM games"); //Get list of game_ids in Database
			ResultSet rs = ps.executeQuery(); 
	
			while (rs.next()) { 
				int gameIdInDB = rs.getInt("game_id");
				gameIds.add(gameIdInDB);
			}
			
			Game updatedGame = util.getObjectForJSON(gameJSON, Game.class);
			Statement statement = connection.createStatement();
			
			if(gameIds.contains(updatedGame.getGame_id())) {
			
				String sqlQuery = "UPDATE games SET title = '" + updatedGame.getTitle() + "', release_date = '" 
						+ updatedGame.getRelease_date() + "', description = '" + updatedGame.getDescription() + "', image_url = '" 
						+ updatedGame.getImage_url() + "', age_rating = '" + updatedGame.getAge_rating() + "', developer_id = " 
						+ updatedGame.getDeveloper_id() + ", genre_id = " + updatedGame.getGenre_id() + 
						" WHERE game_id = " + updatedGame.getGame_id();
				statement.executeUpdate(sqlQuery);
				
			} else { return "{\"message\": \"game_id " + updatedGame.getGame_id() + " not found in database!\"}"; } 
			//Checks whether game exists in DB by checking id
			
			updatedGameId = updatedGame.getGame_id();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return util.getJSONForObject(e);
			
		} finally { 
			if(connection != null) {
				try { 
					connection.close();
					System.out.println("Connection closed.");
				} catch (SQLException e) {
					e.printStackTrace(); }
			}
		}
		
		return "{\"message\": \"Game with id = " + updatedGameId + " successfully updated\"}"; 
		
	}

	@Override
	public String deleteGame(int game_id) {
		
		List<Integer> gameIds = new ArrayList<>(); 
		JDBCConnection jdbcConnection = new JDBCConnection();
		Connection connection = jdbcConnection.getConnnection();
		System.out.println("Connection established.");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT game_id FROM games");
			ResultSet rs = ps.executeQuery(); 
	
			while (rs.next()) { 
				int gameIdInDB = rs.getInt("game_id");
				gameIds.add(gameIdInDB);
			}
			
			Statement statement = connection.createStatement();
			if(gameIds.contains(game_id)) {
			
				String sqlQuery = "DELETE FROM games WHERE game_id = " + game_id;
				statement.executeUpdate(sqlQuery);
				
			} else { return "{\"message\": \"game_id " + game_id + " not found in database!\"}"; }
			
		} catch (SQLException e) {
			e.printStackTrace();
			return util.getJSONForObject(e);
			
		} finally { 
			if(connection != null) {
				try { 
					connection.close();
					System.out.println("Connection closed.");
				} catch (SQLException e) {
					e.printStackTrace(); }
			}
		}
		
		return "{\"message\": \"Game with id = " + game_id + " successfully deleted\"}"; 
		
	}
	
	

}
