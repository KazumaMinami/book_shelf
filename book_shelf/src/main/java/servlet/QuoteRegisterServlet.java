package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.QuoteLogic;
import model.QuoteModel;
import model.UserModel;
import validation.QuoteValidation;

/**
 * Servlet implementation class QuoteRegisterServlet
 */
@WebServlet("/QuoteRegister")
public class QuoteRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuoteRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//book.id
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		request.setAttribute("bookId", bookId);
		
		//book.title, book.author
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		
		//System.out.println("book.title:" + title + " book.author:" + author);
		
		Map<String, String> quotes = new HashMap<String, String>();
		quotes.put("title", title);
		quotes.put("author", author);
		request.setAttribute("quotes", quotes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quoteRegister.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//リクエストパラメータ
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String quote = request.getParameter("quote");
		String page = request.getParameter("page");
		
		//バリデーション
		QuoteValidation validate = new QuoteValidation(request);
		Map<String, String> error = validate.validate();
		
		if(validate.hasErrors()) {
			request.setAttribute("error", error);
			
			Map<String, String> quotes = new HashMap<String, String>();
			quotes.put("bookId", bookId);
			quotes.put("title", title);
			quotes.put("author", author);
			quotes.put("quote", quote);
			quotes.put("page", page);
			request.setAttribute("quotes", quotes);
			
			//book.id
			request.setAttribute("bookId", bookId);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quoteRegister.jsp");
			dispatcher.forward(request, response);
		}
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel)session.getAttribute("user");
		
		QuoteModel model = new QuoteModel();
		model.setUserId(user.getId());
		model.setBookId(Integer.parseInt(bookId));
		model.setQuote(quote);
		model.setPage(Integer.parseInt(page));
		int isDeleted = 0;
		model.setIsDeleted(isDeleted);
		
		//ロジック呼び出し
		QuoteLogic logic = new QuoteLogic();
		if(!logic.create(model)) {
			request.setAttribute("model", model);
			request.setAttribute("id", bookId);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookDetail.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/Main");
		
	}

}
