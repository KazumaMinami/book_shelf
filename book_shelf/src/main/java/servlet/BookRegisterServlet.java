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
 * Servlet implementation class BookRegisterServlet
 */
@WebServlet("/BookRegister")
public class BookRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookRegister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//リクエストパラメータ
		String registrationDate = request.getParameter("registrationDate");
		String finishedDate = null;
		if(request.getParameter("status").equals("3")) {
			java.util.Date date = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			finishedDate = format.format(date);
		}
		String title = request.getParameter("bookTitle");
		String author = request.getParameter("author");
		String status = request.getParameter("status");
		String thoughts = request.getParameter("thoughts");
		
		/*String isDeleted = "0";
		if(request.getParameter("isDeleted") != null) {
			isDeleted = "1";
		}*/
		
		//バリデーション
		BookValidation validate = new BookValidation(request);
		Map<String, String> error = validate.validate();
		
		if(validate.hasErrors()) {
			request.setAttribute("error", error);
			
			Map<String, String> book = new HashMap<String, String>();
			book.put("registrationDate", registrationDate);
			book.put("finishedDate", finishedDate);
			book.put("title", title);
			book.put("author", author);
			book.put("status", status);
			book.put("thoughts", thoughts);
			request.setAttribute("book", book);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookRegister.jsp");
			dispatcher.forward(request, response);
		}
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel)session.getAttribute("user");
		
		BookModel model = new BookModel();
		model.setUserId(user.getId());
		model.setRegistrationDate(Date.valueOf(registrationDate));
		if(finishedDate == null) {
			model.setFinishedDate(null);
		}else {
			model.setFinishedDate(Date.valueOf(finishedDate));
		}
		model.setTitle(title);
		model.setAuthor(author);
		model.setStatus(Integer.parseInt(status));
		model.setThoughts(thoughts);
		model.setIsDeleted(0);
		
		//ロジック呼び出し
		BookLogic logic = new BookLogic();
		if(!logic.create(model)) {
			request.setAttribute("model", model);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/Main");
		
	}
}
