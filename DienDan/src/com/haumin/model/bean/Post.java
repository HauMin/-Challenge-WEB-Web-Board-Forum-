package com.haumin.model.bean;

public class Post {
	private int id_post;
	private String content_post;
	private User user;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int id_post, String content_post, User user) {
		super();
		this.id_post = id_post;
		this.content_post = content_post;
		this.user = user;
	}

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}

	public String getContent_post() {
		return content_post;
	}

	public void setContent_post(String content_post) {
		this.content_post = content_post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
