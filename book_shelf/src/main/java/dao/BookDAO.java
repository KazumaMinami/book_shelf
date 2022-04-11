package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.BookModel;
import model.UserModel;
import settings.DatabaseSettings;

public class BookDAO {
	
	private final String BASE_SQL = "select "
			+ "b.id,"
			+ "b.user_id,"
			+ "b.registration_date,"
			+ "b.finished_date,"
			+ "b.title,"
			+ "b.author,"
			+ "b.status,"
			+ "b.thoughts,"
			+ "b.is_deleted,"
			+ "b.created_at,"
			+ "b.updated_at,"
			+ "u.email,"
			+ "u.password,"
			+ "u.name,"
			+ "u.is_deleted as user_is_deleted,"
			+ "u.created_at as user_created_at,"
			+ "u.updated_at as user_updated_at "
			+ "from books b "
			+ "inner join users u on b.user_id=u.id ";
	
	String DRIVER_NAME = DatabaseSettings.DRIVER_NAME;
	String JDBC_URL = DatabaseSettings.JDBC_URL;
	String DB_USER = DatabaseSettings.DB_USER;
	String DB_PASS= DatabaseSettings.DB_PASS;
	
	//アイテム全件取得
	public List<BookModel> findAll(){
		List<BookModel> list = new ArrayList<BookModel>();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = BASE_SQL + "where b.is_deleted=0 and u.is_deleted=0 "
					+ "order by b.id desc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				BookModel model = new BookModel();
				model.setId(rs.getInt("id"));
				model.setUserId(rs.getInt("user_id"));
				model.setRegistrationDate(Date.valueOf(rs.getString("registration_date")));
				model.setFinishedDate(Date.valueOf(rs.getString("finished_date")));
				model.setTitle(rs.getString("title"));
				model.setAuthor(rs.getString("author"));
				model.setStatus(rs.getInt("status"));
				model.setThoughts(rs.getString("thoughts"));
				model.setIsDeleted(rs.getInt("is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("updated_at")));
				
				UserModel userModel = new UserModel();
				userModel.setEmail(rs.getString("email"));
				userModel.setPassword(rs.getString("password"));
				userModel.setName(rs.getString("name"));
				userModel.setIsDeleted(rs.getInt("user_is_deleted"));
				userModel.setCreatedAt(Timestamp.valueOf(rs.getString("user_created_at")));
				userModel.setUpdatedAt(Timestamp.valueOf(rs.getString("user_updated_at")));
				
				model.setUserModel(userModel);
				
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
	
	
	
	//アイテム1件検索
	public BookModel findOne(int id, int userId) {
		BookModel model = new BookModel();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = BASE_SQL 
					+ "where b.is_deleted=0 "
					+ "and u.is_deleted=0 "
					+ "and b.id=? "
					+ "and b.user_id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			stmt.setInt(2, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				model.setId(rs.getInt("id"));
				model.setUserId(rs.getInt("user_id"));
				model.setRegistrationDate(Date.valueOf(rs.getString("registration_date")));
				if(rs.getString("finished_date") == null) {
					model.setFinishedDate(null);
				}else {
					model.setFinishedDate(Date.valueOf(rs.getString("finished_date")));
				}
				model.setTitle(rs.getString("title"));
				model.setAuthor(rs.getString("author"));
				model.setStatus(rs.getInt("status"));
				model.setThoughts(rs.getString("thoughts"));
				model.setIsDeleted(rs.getInt("is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("updated_at")));
				
				UserModel userModel = new UserModel();
				userModel.setEmail(rs.getString("email"));
				userModel.setPassword(rs.getString("password"));
				userModel.setName(rs.getString("name"));
				userModel.setIsDeleted(rs.getInt("user_is_deleted"));
				userModel.setCreatedAt(Timestamp.valueOf(rs.getString("user_created_at")));
				userModel.setUpdatedAt(Timestamp.valueOf(rs.getString("user_updated_at")));
				
				model.setUserModel(userModel);
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
	
	
	
	//指定したユーザーのブックアイテムを取得
	public List<BookModel> findByUserId(int userId){
		List<BookModel> list = new ArrayList<BookModel>();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = BASE_SQL
					+ "where b.is_deleted=0 "
					+ "and b.user_id=? "
					+ "order by b.id desc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				BookModel model = new BookModel();
				model.setId(rs.getInt("id"));
				model.setUserId(rs.getInt("user_id"));
				model.setRegistrationDate(Date.valueOf(rs.getString("registration_date")));
				if(rs.getString("finished_date") == null) {
					model.setFinishedDate(null);
				}else {
					model.setFinishedDate(Date.valueOf(rs.getString("finished_date")));
				}
				model.setTitle(rs.getString("title"));
				model.setAuthor(rs.getString("author"));
				model.setStatus(rs.getInt("status"));
				model.setThoughts(rs.getString("thoughts"));
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
	
	
	
	//レコード件数を取得
	public int countByUserId(int userId) {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "select count(b.id) as cnt "
					+ "from books b "
					+ "inner join users u on b.user_id=u.id "
					+ "where b.is_deleted=0 "
					+ "and b.user_id=? "
					+ "order by b.id desc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
	}
	
	
	
	//キーワード検索
	public List<BookModel> findByKeyWord(int userId, String keyWord){
		List<BookModel> list = new ArrayList<BookModel>();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = BASE_SQL
					+ "where b.is_deleted=0 "
					+ "and b.user_id=? "
					+ "and b.title like ? "
					+ "or b.author like ? "
					+ "or b.thoughts like ? "
					+ "order by b.id desc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			stmt.setString(2, "%" + keyWord + "%");
			stmt.setString(3, "%" + keyWord + "%");
			stmt.setString(4, "%" + keyWord + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				BookModel model = new BookModel();
				model.setId(rs.getInt("id"));
				model.setUserId(rs.getInt("user_id"));
				model.setRegistrationDate(Date.valueOf(rs.getString("registration_date")));
				if(rs.getString("finished_date") == null) {
					model.setFinishedDate(null);
				}else {
					model.setFinishedDate(Date.valueOf(rs.getString("finished_date")));
				}
				model.setTitle(rs.getString("title"));
				model.setAuthor(rs.getString("author"));
				model.setStatus(rs.getInt("status"));
				model.setThoughts(rs.getString("thoughts"));
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
	
	
	
	//アイテム1件追加
	public boolean create(BookModel model) {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "insert into books ("
					+ "user_id,"
					+ "registration_date,"
					+ "finished_date,"
					+ "title,"
					+ "author,"
					+ "status,"
					+ "thoughts,"
					+ "is_deleted"
					+ ") values ("
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?"
					+ ")";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, model.getUserId());
			stmt.setString(2, model.getRegistrationDate().toString());
			if(model.getFinishedDate() == null) {
				stmt.setString(3,  null);
			}else {
				stmt.setString(3,  model.getFinishedDate().toString());
			}
			stmt.setString(4,  model.getTitle());
			stmt.setString(5,  model.getAuthor());
			stmt.setInt(6,  model.getStatus());
			stmt.setString(7,  model.getThoughts());
			stmt.setInt(8,  model.getIsDeleted());
			
			stmt.executeUpdate();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	//アイテムの更新
	public boolean update(BookModel model) {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "update books set "
					+ "user_id=?,"
					+ "registration_date=?,"
					+ "finished_date=?,"
					+ "title=?,"
					+ "author=?,"
					+ "status=?,"
					+ "thoughts=?,"
					+ "is_deleted=? "
					+ "where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, model.getUserId());
			stmt.setString(2, model.getRegistrationDate().toString());
			if(model.getFinishedDate() == null) {
				stmt.setString(3, null);
			}else {
				stmt.setString(3, model.getFinishedDate().toString());
			}
			stmt.setString(4, model.getTitle());
			stmt.setString(5, model.getAuthor());
			stmt.setInt(6, model.getStatus());
			stmt.setString(7, model.getThoughts());
			stmt.setInt(8, model.getIsDeleted());
			stmt.setInt(9, model.getId());
			
			stmt.executeUpdate();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
	
	

}
