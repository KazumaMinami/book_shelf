package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.BookLogic;
import logic.QuoteLogic;
import model.BookModel;
import model.QuoteModel;
import model.UserModel;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet("/BookDetail")
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailServlet() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		
		//セッションID
		HttpSession session = request.getSession();
		UserModel user = (UserModel)session.getAttribute("user");
		
		//本の検索
		BookLogic logic = new BookLogic();
		BookModel book = logic.find(id, user.getId());
		
		//スコープに保存
		request.setAttribute("book", book);
		
		//引用の検索
		QuoteLogic quoteLogic = new QuoteLogic();
		List<QuoteModel> items = quoteLogic.find(book.getId(), user.getId());
		
		/*for(QuoteModel item : items) {
			System.out.println("データ格納のチェック：" + item.getPage());
		}*/
		
		
		//スコープに保存
		request.setAttribute("items", items);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
