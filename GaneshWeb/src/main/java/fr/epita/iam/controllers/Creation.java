package fr.epita.iam.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.ganeshcore.services.HibernetIdentityDAO;
import fr.epita.ganeshcore.datamodel.Identity;
import fr.epita.ganeshcore.services.HibernetIdentityDAO;

/**
 * Servlet implementation class Creation
 */
public class Creation extends AbstractSpringIdentity {
	private static final long serialVersionUID = 1L;
     @Inject  
	HibernetIdentityDAO dao;
    public Creation() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String displayName = request.getParameter("displayName");
		final String email = request.getParameter("email");
		final String rawDate = request.getParameter("date");
		if (!displayName.isEmpty() && !email.isEmpty() && !rawDate.isEmpty() ) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	try {
		final Date date = sdf.parse(rawDate);
        
		final Identity identity = new Identity(displayName, email, date);

		dao.save(identity);
        
		

		response.sendRedirect("Success.jsp");

	} catch (final Exception pe) {
		request.getSession().setAttribute("message",
				"A problem occured with the identity creation, contact the admistrator at ...@admin.com");
		response.sendRedirect("creation.jsp");
	}
	

}else {
	
	response.sendRedirect("identityempty.jsp");
}

}

	}


