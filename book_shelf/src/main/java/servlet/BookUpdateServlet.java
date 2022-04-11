package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.BookLogic;
import model.BookModel;
import model.UserModel;
import validation.BookValidation;

/**
 * Servlet implementation class BookUpdateServlet
 */
@WebServlet("/BookUpdate")
public class BookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//リクエストパラメータ book.id
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel)session.getAttribute("user");
		
		//検索
		BookLogic logic = new BookLogic();
		BookModel book = logic.find(Integer.parseInt(id), user.getId());
		
		//スコープに保存
		request.setAttribute("book", book);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookUpdate.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//リクエストパラメータ
		String id = request.getParameter("id");
		String registrationDate = request.getParameter("registrationDate");
		String finishedDate = null;
		if(!request.getParameter("status").equals("3")){
			finishedDate = null;
		}else if(request.getParameter("finishedDate") != null) {
			finishedDate = request.getParameter("finishedDate");
		}else if(request.getParameter("status").equals("3")) {
			java.util.Date date = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			finishedDate = format.format(date);
		}else {
			finishedDate = null;
		}
		String title = request.getParameter("bookTitle");
		String author = request.getParameter("author");
		String status = request.getParameter("status");
		String thoughts = request.getParameter("thoughts");
		String isDeleted = "0";
		if(request.getParameter("isDeleted") != null) {
			isDeleted = "1";
		}
		
		//バリデーション
		BookValidation validate = new BookValidation(request);
		Map<String, String> error = validate.validate();
		
		if(validate.hasErrors()) {
			request.setAttribute("error", error);
			
			Map<String, String> book = new HashMap<String, String>();
			book.put("id", id);
			book.put("registrationDate", registrationDate);
			book.put("finishedDate", finishedDate);
			book.put("title", title);
			book.put("author", author);
			book.put("status", status);
			book.put("thoughts", thoughts);
			book.put("isDeleted", isDeleted);
			request.setAttribute("book", book);
			
			//id
			request.setAttribute("bookId", id);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel)session.getAttribute("user");
		
		BookModel model = new BookModel();
		model.setId(Integer.parseInt(id));
		model.setUserId(user.getId());
		model.setRegistrationDate(Date.valueOf(registrationDate));
		if(!status.equals("3")) {
			//System.out.println("finishedDate:" + finishedDate);
			model.setFinishedDate(null);
		}else if(status.equals("3")){
			//System.out.println("finishedDate:3" + finishedDate);
			if(finishedDate.length() == 0) {
				java.util.Date date = new java.util.Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				finishedDate = format.format(date);
				model.setFinishedDate(Date.valueOf(finishedDate));
			}else {
				model.setFinishedDate(Date.valueOf(finishedDate));
			}
		}
		model.setTitle(title);
		model.setAuthor(author);
		model.setStatus(Integer.parseInt(status));
		model.setThoughts(thoughts);
		model.setIsDeleted(Integer.parseInt(isDeleted));
		
		BookLogic logic = new BookLogic();
		if(!logic.update(model)) {
			request.setAttribute("model", model);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/Main");
		
		
	}

}
