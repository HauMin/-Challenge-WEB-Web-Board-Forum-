package com.haumin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.haumin.model.bean.User;
import com.haumin.untils.DatabaseConnection;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public User checkLogin(String username, String password) {
		User objUser = null;
		conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM user WHERE username = ? && password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"),
						rs.getString("display_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		return objUser;
	}

	public int addUser(User user) {
		int result = 0;

		String sql = "INSERT INTO user(username,password,display_name) VALUES (?,?,?) ";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getDisplay_name());
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
