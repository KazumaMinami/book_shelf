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
import model.QuoteModel;
import settings.DatabaseSettings;

public class QuoteDAO {
	
	String DRIVER_NAME = DatabaseSettings.DRIVER_NAME;
	String JDBC_URL = DatabaseSettings.JDBC_URL;
	String DB_USER = DatabaseSettings.DB_USER;
	String DB_PASS = DatabaseSettings.DB_PASS;
	
	
	
	//1件検索
	public QuoteModel findOne(int id) {
		QuoteModel model = new QuoteModel();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			/*String sql = "select * from quotes "
					+ "where id=?";*/
			
			String sql = "select "
					+ "q.id,"
					+ "q.user_id,"
					+ "q.book_id,"
					+ "q.quote,"
					+ "q.pp,"
					+ "q.is_deleted,"
					+ "q.created_at,"
					+ "q.updated_at,"
					+ "b.id,"
					+ "b.user_id,"
					+ "b.registration_date,"
					+ "b.finished_date,"
					+ "b.title,"
					+ "b.author,"
					+ "b.status,"
					+ "b.thoughts,"
					+ "b.is_deleted as book_is_deleted,"
					+ "b.created_at as book_created_at,"
					+ "b.updated_at as book_updated_at "
					+ "from quotes q "
					+ "inner join books b on q.book_id=b.id "
					+ "where q.is_deleted=0 "
					+ "and q.id=? ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				/*model.setId(rs.getInt("id"));
				model.setUserId(rs.getInt("user_id"));
				model.setBookId(rs.getInt("book_id"));
				model.setQuote(rs.getString("quote"));
				model.setPage(rs.getInt("pp"));
				model.setIsDeleted(rs.getInt("is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("updated_at")));*/
				
				model.setId(rs.getInt("q.id"));
				model.setUserId(rs.getInt("q.user_id"));
				model.setBookId(rs.getInt("q.book_id"));
				model.setQuote(rs.getString("q.quote"));
				model.setPage(rs.getInt("q.pp"));
				model.setIsDeleted(rs.getInt("q.is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("q.created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("q.updated_at")));
				
				BookModel bookModel = new BookModel();
				bookModel.setId(rs.getInt("b.id"));
				bookModel.setUserId(rs.getInt("b.user_id"));
				bookModel.setRegistrationDate(Date.valueOf(rs.getString("b.registration_date")));
				if(rs.getString("finished_date") == null) {
					bookModel.setFinishedDate(null);
				}else {
					bookModel.setFinishedDate(Date.valueOf(rs.getString("b.finished_date")));
				}
				
				bookModel.setTitle(rs.getString("b.title"));
				bookModel.setAuthor(rs.getString("b.author"));
				bookModel.setStatus(rs.getInt("b.status"));
				bookModel.setThoughts(rs.getString("b.thoughts"));
				bookModel.setIsDeleted(rs.getInt("book_is_deleted"));
				bookModel.setCreatedAt(Timestamp.valueOf(rs.getString("book_created_at")));
				bookModel.setUpdatedAt(Timestamp.valueOf(rs.getString("book_updated_at")));
				
				model.setBookModel(bookModel);
				
				
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
	
	
	
	//ユーザーごとの全件取得
	public List<QuoteModel> findByUserId(int userId){
		List<QuoteModel> list = new ArrayList<QuoteModel>();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "select "
					+ "q.id,"
					+ "q.user_id,"
					+ "q.book_id,"
					+ "q.quote,"
					+ "q.pp,"
					+ "q.is_deleted,"
					+ "q.created_at,"
					+ "q.updated_at,"
					+ "b.id,"
					+ "b.user_id,"
					+ "b.registration_date,"
					+ "b.finished_date,"
					+ "b.title,"
					+ "b.author,"
					+ "b.status,"
					+ "b.thoughts,"
					+ "b.is_deleted as book_is_deleted,"
					+ "b.created_at as book_created_at,"
					+ "b.updated_at as book_updated_at "
					+ "from quotes q "
					+ "inner join books b on q.book_id=b.id "
					+ "where q.is_deleted=0 "
					+ "and q.user_id=? "
					+ "order by q.id desc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				QuoteModel model = new QuoteModel();
				
				model.setId(rs.getInt("q.id"));
				model.setUserId(rs.getInt("q.user_id"));
				model.setBookId(rs.getInt("q.book_id"));
				model.setQuote(rs.getString("q.quote"));
				model.setPage(rs.getInt("q.pp"));
				model.setIsDeleted(rs.getInt("q.is_deleted"));
				model.setCreatedAt(Timestamp.valueOf(rs.getString("q.created_at")));
				model.setUpdatedAt(Timestamp.valueOf(rs.getString("q.updated_at")));
				
				BookModel bookModel = new BookModel();
				bookModel.setId(rs.getInt("b.id"));
				bookModel.setUserId(rs.getInt("b.user_id"));
				bookModel.setRegistrationDate(Date.valueOf(rs.getString("b.registration_date")));
				if(rs.getString("finished_date") == null) {
					bookModel.setFinishedDate(null);
				}else {
					bookModel.setFinishedDate(Date.valueOf(rs.getString("b.finished_date")));
				}
				bookModel.setTitle(rs.getString("b.title"));
				bookModel.setAuthor(rs.getString("b.author"));
				bookModel.setStatus(rs.getInt("b.status"));
				bookModel.setThoughts(rs.getString("b.thoughts"));
				bookModel.setIsDeleted(rs.getInt("book_is_deleted"));
				bookModel.setCreatedAt(Timestamp.valueOf(rs.getString("book_created_at")));
				bookModel.setUpdatedAt(Timestamp.valueOf(rs.getString("book_updated_at")));
				
				model.setBookModel(bookModel);
				
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
				}
			}
		}
		return list;
	}
	
	
	
	//本ごとに全件取得
	public List<QuoteModel> findByBookId(int bookId, int userId){
		List<QuoteModel> list = new ArrayList<QuoteModel>();
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "select * from quotes "
					+ "where is_deleted=0 and user_id=? and book_id=? "
					+ "order by pp asc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			stmt.setInt(2, bookId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				QuoteModel model = new QuoteModel();
				
				model.setId(rs.getInt("id"));
				model.setUserId(rs.getInt("user_id"));
				model.setBookId(rs.getInt("book_id"));
				model.setQuote(rs.getString("quote"));
				model.setPage(rs.getInt("pp"));
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
	
	
	
	//1件追加
	public boolean create(QuoteModel model) {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "insert into quotes ("
					+ "user_id,"
					+ "book_id,"
					+ "quote,"
					+ "pp,"
					+ "is_deleted"
					+ ") values ("
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?"
					+ ")";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, model.getUserId());
			stmt.setInt(2, model.getBookId());
			stmt.setString(3, model.getQuote());
			stmt.setInt(4, model.getPage());
			stmt.setInt(5, model.getIsDeleted());
			
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
	
	
	
	public boolean update(QuoteModel model) {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "update quotes set "
					+ "user_id=?,"
					+ "book_id=?,"
					+ "quote=?,"
					+ "pp=?,"
					+ "is_deleted=? "
					+ "where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, model.getUserId());
			stmt.setInt(2, model.getBookId());
			stmt.setString(3, model.getQuote());
			stmt.setInt(4, model.getPage());
			stmt.setInt(5,  model.getIsDeleted());
			stmt.setInt(6, model.getId());
			
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
