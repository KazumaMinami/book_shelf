package logic;

import java.util.List;

import dao.UserDAO;
import model.UserModel;

public class UserLogic {
	
	public int create(UserModel model) {
		UserDAO dao = new UserDAO();
		
		return dao.create(model);
	}
	
	
	public List<UserModel> find(){
		UserDAO dao = new UserDAO();
		
		List<UserModel> list = dao.findAll();
		return list;
	}
	
	
	public UserModel find(int userId) {
		UserDAO dao = new UserDAO();
		
		return dao.findOne(userId);
	}
	
	
	public UserModel find(String email, String password) {
		UserDAO dao = new UserDAO();
		
		return dao.findOne(email, password);
	}
	
	
	public int update(UserModel model) {
		UserDAO dao = new UserDAO();
		
		return dao.update(model);
	}
	

}
