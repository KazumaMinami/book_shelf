package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<BookModel> items = new ArrayList<BookModel>();
		
		BookLogic logic = new BookLogic();
		HttpSession session = request.getSession();
		UserModel user = (UserModel)session.getAttribute("user");
		if(request.getParameter("key") != null) {
			System.out.println("キーワード検索の動作チェック");
			items = logic.find(user.getId(), request.getParameter("key"));
			
			request.setAttribute("key", request.getParameter("key"));
		}else {
			items = logic.find(user.getId());
		}
		request.setAttribute("items", items);
		
		//登録件数情報
		int cnt = logic.count(user.getId());
		request.setAttribute("cnt", cnt);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
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
