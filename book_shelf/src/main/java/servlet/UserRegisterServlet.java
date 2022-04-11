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

import logic.UserLogic;
import model.UserModel;
import settings.DatabaseSettings;
import settings.MessageSettings;
import validation.UserValidation;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		UserValidation validate = new UserValidation(request);
		Map<String, String> error = validate.validate();
		
		if(validate.hasErrors()) {
			request.setAttribute("error", error);
			
			Map<String, String> user = new HashMap<String, String>();
			user.put("email", email);
			user.put("password", password);
			user.put("name", name);
			request.setAttribute("user", user);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
			dispatcher.forward(request, response);
		}
		
		UserModel user = new UserModel();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		
		UserLogic logic = new UserLogic();
		int ret = logic.create(user);
		
		switch(ret) {
		case DatabaseSettings.DB_EXECUTION_SUCCESS:
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		case DatabaseSettings.DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME:
			request.setAttribute("db_error", String.format(MessageSettings.MSG_ER_DUP_KEYNAME, user.getEmail()));
			break;
		default:
			request.setAttribute("db_error", MessageSettings.MSG_ERROR_OCCURRED);
			break;
		}
		
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
		dispatcher.forward(request, response);
		
	}

}
