package com.haumin.model.bean;

public class User {
	private int id_user;
	private String username;
	private String password;
	private String display_name;

	public User() {
		super();
	}

	public User(int id_user, String username, String password, String display_name) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.password = password;
		this.display_name = display_name;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

}
