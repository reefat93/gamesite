package com.reefat.gamesite.persistence;

public class GameComplete {
	
	private int game_id;
	private String title;
	private	String release_date;
	private String description;
	private String image_url;
	private String age_rating;
	private String developer_name;
	private String genre_name;
	
	public GameComplete() {
		//Empty Constructor
	}
	
	public GameComplete(int game_id, String title, String release_date, String description, String image_url, String age_rating,
			String developer_name, String genre_name) {
		this.game_id = game_id;
		this.title = title;
		this.release_date = release_date;
		this.description = description;
		this.image_url = image_url;
		this.age_rating = age_rating;
		this.developer_name = developer_name;
		this.genre_name = genre_name;
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
	public String getAge_rating() {
		return age_rating;
	}
	public void setAge_rating(String age_rating) {
		this.age_rating = age_rating;
	}
	public String getDeveloper_name() {
		return developer_name;
	}
	public void setDeveloper_name(String developer_name) {
		this.developer_name = developer_name;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

}
