package app;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Admin;

/**
 * Servlet implementation class CheckUser
 */
@WebServlet("/checkuser")
public class CheckUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("logout")!=null) {
			HttpSession sessio=request.getSession(true);
			sessio.invalidate();
			sessio=null;
		}
		response.sendRedirect("/jsp/adminlogin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		// check with database table called admin
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// name of project which is mentioned in persistence
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Admin q=em.find(Admin.class, username);
		em.getTransaction().commit();
		
		//Checking the user and pass (should be checked from the database)
		if (username.compareTo(q.getUsername())==0 && password.compareTo(q.getPassword())==0) {
			HttpSession sessio=request.getSession(true);
			sessio.setAttribute("AuthOk", "ok");
			response.sendRedirect("/jsp/adminmain.jsp");
		}
		else {
			response.sendRedirect("/jsp/adminlogin.jsp");//Or perhaps to register page
		}
	}

}
