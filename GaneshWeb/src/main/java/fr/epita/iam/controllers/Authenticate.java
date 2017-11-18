package fr.epita.iam.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;

import fr.epita.ganeshcore.services.HibernetIdentityDAO;

/**
 * Servlet implementation class Authenticate
 */
public class Authenticate extends AbstractSpringIdentity {
	private static final long serialVersionUID = 1L;
	@Inject
	SessionFactory sf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String login = request.getParameter("firstName");
		final String password = request.getParameter("password");
		
		final HttpSession session = request.getSession();
	    // TODO you have to implement a *REAL* authentication mechanism
         if ("kafle".equals(login)&&"kafle".equals(password)) {
			// then we are authenticated, we can move to the welcome page
			session.setAttribute("authenticated", true);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
			


		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			session.setAttribute("authenticated", false);
		}
	}

	}


