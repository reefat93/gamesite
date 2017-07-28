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

public class DBGameService implements GameService {
	
	@Inject
	private JSONUtil util;

	@Override
	public String getAllGames() {

		List<Game> gameData = new ArrayList<>();
		JDBCConnection jdbcConnection = new JDBCConnection();
		Connection connection = jdbcConnection.getConnnection();
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM games");
			ResultSet rs = ps.executeQuery();
	
			while (rs.next()) {
				Game game = new Game();
				game.setGame_id(rs.getInt("game_id"));
				game.setTitle(rs.getString("title"));
				game.setRelease_date(rs.getString("release_date"));
				game.setDescription(rs.getString("description"));
				game.setImage_url(rs.getString("image_url"));
				game.setAge_rating(rs.getString("age_rating"));
				game.setGenre_id(rs.getInt("genre_id"));
				game.setDeveloper_id(rs.getInt("developer_id"));
				gameData.add(game);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return util.getJSONForObject(gameData);
	}

	@Override
	public String getAllGamesComplete() {
		// TODO Auto-generated method stub
		return null;
	}

}
