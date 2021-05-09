package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
		, urlPatterns = { "/jsp/protected.jsp","/fromfilter", "/rest/signinneeded/*","/jsp/adminmain.jsp","/jsp/question/questionform.jsp",
				"/jsp/question/questiontoupdateform.jsp","/jsp/candidate/candidateform.jsp","/jsp/candidate/candidatetoupdateform.jsp",
				"/rest/candidateservice/deletecandidate","/rest/candidateservice/updatecandidate",
				"/rest/candidateservice/readtoupdatecandidate","/rest/questionservice/updatequestion",
				"/rest/questionservice/readtoupdatequestion","/rest/questionservice/deletequestion",
				"/rest/questionservice/addquestion","/addquestion","/addcandidate","/rest/candidateservice/addcandidate",})
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
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
		HttpSession sessio=((HttpServletRequest)request).getSession(true);
		String ok=(String) sessio.getAttribute("AuthOk");
		if (ok==null) {
			((HttpServletResponse) response).sendRedirect("/jsp/adminlogin.jsp");
			return;
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