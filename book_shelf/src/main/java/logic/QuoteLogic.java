package logic;

import java.util.List;

import dao.QuoteDAO;
import model.QuoteModel;

public class QuoteLogic {
	
	public QuoteModel findOne(int id) {
		QuoteDAO dao = new QuoteDAO();
		
		return dao.findOne(id);
	}
	
	
	public List<QuoteModel> find(int userId){
		QuoteDAO dao = new QuoteDAO();
		
		return dao.findByUserId(userId);
	}
	
	
	public List<QuoteModel> find(int bookId, int userId){
		QuoteDAO dao = new QuoteDAO();
		
		return dao.findByBookId(bookId, userId);
	}
	
	
	public boolean create(QuoteModel model) {
		QuoteDAO dao = new QuoteDAO();
		
		return dao.create(model);
	}
	
	
	public boolean update(QuoteModel model) {
		QuoteDAO dao = new QuoteDAO();
		
		return dao.update(model);
	}

}
