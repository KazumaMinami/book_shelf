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
import validation.QuoteUpdateValidation;

/**
 * Servlet implementation class QuoteUpdateServlet
 */
@WebServlet("/QuoteUpdate")
public class QuoteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuoteUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//リクエストパラメータ
		int quoteId = Integer.parseInt(request.getParameter("id"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		request.setAttribute("quoteId", quoteId);
		request.setAttribute("bookId", bookId);
		
		//引用IDで検索
		QuoteLogic logic = new QuoteLogic();
		QuoteModel model = logic.findOne(quoteId);
		request.setAttribute("model", model);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quoteUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//リクエストパラメータ
		//String title = request.getParameter("title");
		//String author = request.getParameter("author");
		String bookId = request.getParameter("bookId");
		String quote = request.getParameter("quote");
		String page  = request.getParameter("page");
		String isDeleted = "0";
		if(request.getParameter("isDeleted") != null) {
			isDeleted = "1";
		}
		String quoteId = request.getParameter("quoteId");
		
		//バリデーション
		QuoteUpdateValidation validate = new QuoteUpdateValidation(request);
		Map<String, String> error = validate.validate();
		
		if(validate.hasErrors()) {
			request.setAttribute("error", error);
			
			Map<String, String> model = new HashMap<String, String>();
			model.put("quote", quote);
			model.put("page", page);
			
			request.setAttribute("model", model);
			
			//quoteId, bookId
			request.setAttribute("quoteId", quoteId);
			request.setAttribute("bookId", bookId);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quoteUpdate.jsp");
			dispatcher.forward(request, response);
		}
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel)session.getAttribute("user");
		
		QuoteModel qModel = new QuoteModel();
		qModel.setId(Integer.parseInt(quoteId));
		qModel.setUserId(user.getId());
		qModel.setBookId(Integer.parseInt(bookId));
		qModel.setQuote(quote);
		qModel.setPage(Integer.parseInt(page));
		qModel.setIsDeleted(Integer.parseInt(isDeleted));
		
		QuoteLogic logic = new QuoteLogic();
		if(!logic.update(qModel)) {
			request.setAttribute("qModel", qModel);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quoteUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/Main");
	}

}
