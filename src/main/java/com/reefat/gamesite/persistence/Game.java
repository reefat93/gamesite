package com.reefat.gamesite.persistence;

public class Game {
	
	private int game_id;
	private String title;
	private	String release_date;
	private String description;
	private String image_url;
	private String developer_developer_id;
	private String age_rating;
	private String genres_genre_id;
	
	public Game() {
		//Empty Constructor
	}
	
	public Game(int game_id, String title, String release_date, String description, String image_url,
			String developer_developer_id, String age_rating, String genres_genre_id) {
		this.game_id = game_id;
		this.title = title;
		this.release_date = release_date;
		this.description = description;
		this.image_url = image_url;
		this.developer_developer_id = developer_developer_id;
		this.age_rating = age_rating;
		this.genres_genre_id = genres_genre_id;
	}
	
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getDeveloper_developer_id() {
		return developer_developer_id;
	}
	public void setDeveloper_developer_id(String developer_developer_id) {
		this.developer_developer_id = developer_developer_id;
	}
	public String getAge_rating() {
		return age_rating;
	}
	public void setAge_rating(String age_rating) {
		this.age_rating = age_rating;
	}
	public String getGenres_genre_id() {
		return genres_genre_id;
	}
	public void setGenres_genre_id(String genres_genre_id) {
		this.genres_genre_id = genres_genre_id;
	}
	
}
