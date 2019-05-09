package com.haumin.model.bean;

public class Comment {
	private int id_cmt;
	private String content_cmt;
	private User user;
	private Post post;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id_cmt, String content_cmt, User user, Post post) {
		super();
		this.id_cmt = id_cmt;
		this.content_cmt = content_cmt;
		this.user = user;
		this.post = post;
	}

	public int getId_cmt() {
		return id_cmt;
	}

	public void setId_cmt(int id_cmt) {
		this.id_cmt = id_cmt;
	}

	public String getContent_cmt() {
		return content_cmt;
	}

	public void setContent_cmt(String content_cmt) {
		this.content_cmt = content_cmt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
