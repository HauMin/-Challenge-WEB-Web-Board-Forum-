package com.haumin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.haumin.model.bean.Comment;
import com.haumin.model.bean.Post;
import com.haumin.model.bean.User;
import com.haumin.untils.DatabaseConnection;

public class CommentDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<Comment> getItiems(int nid) {
		ArrayList<Comment> list = new ArrayList<>();
		String sql = "SELECT id_cmt, content_cmt, comment.id_user, user.display_name FROM comment INNER JOIN user ON comment.id_user = user.id_user WHERE id_post = (?)";
		conn = DatabaseConnection.getConnection();
		try {

			pst = conn.prepareStatement(sql);
			pst.setInt(1, nid);

			rs = pst.executeQuery();

			while (rs.next()) {
				int id_cmt = rs.getInt("id_cmt");
				String content_cmt = rs.getString("content_cmt");
				int uid = rs.getInt("comment.id_user");
				String display_name = rs.getString("user.display_name");

				User user = new User(uid, "", "", display_name);
				Post post = new Post(nid, "", user);
				Comment cmt = new Comment(id_cmt, content_cmt, user, post);
				list.add(cmt);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public int addComment(Comment cmt) {
		int result = 0;

		String sql = "INSERT INTO comment(content_cmt,id_user,id_post) VALUES (?,?,?)";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cmt.getContent_cmt());
			pst.setInt(2, cmt.getUser().getId_user());
			pst.setInt(3, cmt.getPost().getId_post());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null & pst != null) {
				try {
					conn.close();
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public int deleteItem(int cid) {
		int result = 0;

		String sql = "DELETE FROM comment WHERE id_cmt = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null & pst != null) {
				try {
					conn.close();
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public Comment getItem(int cid) {
		Comment cmt = new Comment();
		String sql = "SELECT content_cmt,id_user,id_post FROM comment WHERE id_cmt = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				String content_cmt = rs.getString("content_cmt");
				int id_user = rs.getInt("id_user");
				int id_post = rs.getInt("id_post");
				User user = new User(id_user, "", "", "");
				Post post = new Post(id_post, "", user);
				cmt = new Comment(cid, content_cmt, user, post);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null && conn != null & pst != null) {
				try {
					rs.close();
					conn.close();
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cmt;
	}

	public int editItem(Comment cmt) {
		int result = 0;

		String sql = "UPDATE comment SET content_cmt = (?) WHERE id_cmt = (?) ";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, cmt.getContent_cmt());
			pst.setInt(2, cmt.getId_cmt());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null & pst != null) {
				try {
					conn.close();
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
