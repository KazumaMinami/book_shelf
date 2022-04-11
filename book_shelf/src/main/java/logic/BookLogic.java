package logic;

import java.util.List;

import dao.BookDAO;
import model.BookModel;

public class BookLogic {
	
	public boolean create(BookModel model) {
		BookDAO dao = new BookDAO();
		
		return dao.create(model);
	}
	
	
	
	public List<BookModel> find(){
		BookDAO dao = new BookDAO();
		
		return dao.findAll();
	}
	
	
	
	public List<BookModel> find(int userId){
		BookDAO dao = new BookDAO();
		
		return dao.findByUserId(userId);
	}
	
	
	
	public BookModel find(int id, int userId) {
		BookDAO dao = new BookDAO();
		
		return dao.findOne(id, userId);
	}
	
	
	
	public List<BookModel> find(int userId, String keyWord){
		BookDAO dao = new BookDAO();
		
		return dao.findByKeyWord(userId, keyWord);
	}
	
	
	
	public boolean update(BookModel model) {
		BookDAO dao = new BookDAO();
		
		return dao.update(model);
	}
	
	
	
	//count
	public int count(int userId) {
		BookDAO dao = new BookDAO();
		
		return dao.countByUserId(userId);
	}

}
