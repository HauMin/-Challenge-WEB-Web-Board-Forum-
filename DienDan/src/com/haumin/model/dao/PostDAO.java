package com.haumin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.haumin.model.bean.Post;
import com.haumin.model.bean.User;
import com.haumin.untils.DatabaseConnection;

public class PostDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<Post> getItiems() {
		ArrayList<Post> list = new ArrayList<>();
		String sql = "SELECT id_post, content_post, post.id_user, display_name FROM post INNER JOIN user ON post.id_user = user.id_user";
		try {
			// tạo connection
			conn = DatabaseConnection.getConnection();

			// statement: bộ thực thi câu lệnh sql
			st = conn.createStatement();

			// resultset: thực thi câu lệnh sql và lưu lại dữ liệu
			rs = st.executeQuery(sql);
			// int result = st.executeUpdate(sql);
			while (rs.next()) {
				int id_post = rs.getInt("id_post");
				String content_post = rs.getString("content_post");

				int id_user = rs.getInt("post.id_user");
				String display_name = rs.getString("display_name");
				User user = new User(id_user, "", "", display_name);
				Post item = new Post(id_post, content_post, user);
				list.add(item);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public int addPosst(Post post) {
		int result = 0;

		String sql = "INSERT INTO post(content_post,id_user) VALUES (?,?) ";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, post.getContent_post());
			pst.setInt(2, post.getUser().getId_user());
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

	public ArrayList<Post> getListPostPersonal(int id_user) {
		ArrayList<Post> list = new ArrayList<>();
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT id_post, content_post, id_user FROM post WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_user);

			rs = pst.executeQuery();
			while (rs.next()) {
				int id_post = rs.getInt("id_post");
				String content_post = rs.getString("content_post");

				int id_user1 = rs.getInt("id_user");
				User user = new User(id_user1, "", "", "");
				Post item = new Post(id_post, content_post, user);
				list.add(item);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public Post getDTByID(int nid) {
		Post post = new Post();
		String sql = "SELECT content_post, post.id_user, user.display_name FROM post INNER JOIN user ON post.id_user = user.id_user WHERE id_post = (?)";
		conn = DatabaseConnection.getConnection();
		try {

			pst = conn.prepareStatement(sql);
			pst.setInt(1, nid);

			rs = pst.executeQuery();

			while (rs.next()) {

				String content_post = rs.getString("content_post");
				String display_name = rs.getString("user.display_name");
				int uid = rs.getInt("post.id_user");
				User user = new User(uid,"","",display_name);
				post = new Post(nid, content_post,user);
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

		return post;
	}

}
