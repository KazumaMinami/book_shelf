package filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import settings.MessageSettings;

/**
 * Servlet Filter implementation class OneTimeTokenCheckFilter
 */
@WebFilter(filterName = "OneTimeTokenCheckFilter")
public class OneTimeTokenCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public OneTimeTokenCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		if(((HttpServletRequest)request).getMethod().equals("POST")) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			
			if(!Objects.equals(request.getParameter("token"), session.getAttribute("token"))) {
				session.removeAttribute("user");
				
				((HttpServletRequest)request).setAttribute("error", MessageSettings.MSG_INVALID_PROCESS);
				
				RequestDispatcher dispatcher = ((HttpServletRequest)request).getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
				
				return;
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
