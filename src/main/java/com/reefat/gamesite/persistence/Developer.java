package com.reefat.gamesite.persistence;

public class Developer {
	
	private int developer_id;
	private String developer_name;
	
	public Developer() {
	}
	
	public Developer(int developer_id, String developer_name) {
		super();
		this.developer_id = developer_id;
		this.developer_name = developer_name;
	}
	
	
	public int getDeveloper_id() {
		return developer_id;
	}
	
	public void setDeveloper_id(int developer_id) {
		this.developer_id = developer_id;
	}
	
	public String getDeveloper_name() {
		return developer_name;
	}
	
	public void setDeveloper_name(String developer_name) {
		this.developer_name = developer_name;
	}
	
	

}
