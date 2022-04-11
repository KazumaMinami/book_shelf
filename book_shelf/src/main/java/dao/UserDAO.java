package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;
import settings.DatabaseSettings;

public class UserDAO {
	
	String DRIVER_NAME = DatabaseSettings.DRIVER_NAME;
	String JDBC_URL = DatabaseSettings.JDBC_URL;
	String DB_USER = DatabaseSettings.DB_USER;
	String DB_PASS = DatabaseSettings.DB_PASS;
	
	
	//全件検索
	public List<UserModel> findAll(){
		
		List<UserModel> list = new ArrayList<UserModel>();
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "select * from users order by id";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				UserModel model = new UserModel();
				model.setId(rs.getInt("id"));
				model.setEmail(rs.getString("email"));
				model.setPassword(rs.getString("password"));
				model.setName(rs.getString("name"));
				model.setIsDeleted(rs.getInt("is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("updated_at")));
				
				list.add(model);
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return list;
		
	}
	
	
	
	public UserModel findOne(int id) {
		UserModel model = new UserModel();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "select * from users where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				model.setId(rs.getInt("id"));
				model.setEmail(rs.getString("email"));
				model.setPassword(rs.getString("password"));
				model.setName(rs.getString("name"));
				model.setIsDeleted(rs.getInt("is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("updated_at")));
			}else {
				model = null;
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return model;
	}
	
	
	
	public UserModel findOne(String email, String password) {
		UserModel model = new UserModel();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "select * from users "
					+ "where is_deleted=0 and email=? and password=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				model.setId(rs.getInt("id"));
				model.setEmail(rs.getString("email"));
				model.setPassword(rs.getString("password"));
				model.setName(rs.getString("name"));
				model.setIsDeleted(rs.getInt("is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("updated_at")));
			}else {
				model = null;
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return model;
	}
	
	
	
	public int create(UserModel model) {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "insert into users "
					+ "(email, password, name, is_deleted) "
					+ "values (?, ?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, model.getEmail());
			stmt.setString(2, model.getPassword());
			stmt.setString(3, model.getName());
			stmt.setInt(4, model.getIsDeleted());
			
			stmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return DatabaseSettings.DB_EXECUTION_FAILURE;
		}catch(SQLException e) {
			e.printStackTrace();
			return e.getErrorCode();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return DatabaseSettings.DB_EXECUTION_FAILURE;
				}
			}
		}
		return DatabaseSettings.DB_EXECUTION_SUCCESS;
	}
	
	
	
	public int update(UserModel model) {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "update users set "
					+ "email=?, password=?, name=?, is_deleted=? "
					+ "where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, model.getEmail());
			stmt.setString(2, model.getPassword());
			stmt.setString(3, model.getName());
			stmt.setInt(4, model.getIsDeleted());
			stmt.setInt(5, model.getId());
			
			stmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return DatabaseSettings.DB_EXECUTION_FAILURE;
		}catch(SQLException e) {
			e.printStackTrace();
			return e.getErrorCode();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return DatabaseSettings.DB_EXECUTION_FAILURE;
				}
			}
		}
		return DatabaseSettings.DB_EXECUTION_SUCCESS;
		
	}
	
	
	
	


}
