package fr.epita.iam.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.DataException;

import fr.epita.ganeshcore.datamodel.Identity;
import fr.epita.ganeshcore.services.HibernetIdentityDAO;





/**
 * Servlet implementation class IdentityModify
 */
public class IdentityModify extends AbstractSpringIdentity {
	private static final long serialVersionUID = 1L;
	
	 @Inject
    HibernetIdentityDAO dao;
    
    private static final Logger LOGGER = LogManager.getLogger(Creation.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentityModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("selection");
        LOGGER.info("value of ID"+id); 
					
					
					if ( id != null )
					{
						
						if (request.getParameter("modify") != null) {  
							 
							Identity identity=dao.getById(Long.parseLong(id));
							request.getSession().setAttribute("identity", identity);
						
						
						
						
						response.sendRedirect("modification.jsp");
					
					
					}
					else if (request.getParameter("delete") != null)
					{
						Identity identity=dao.getById(Integer.parseInt(id));
						try {
							dao.delete(identity);
							response.sendRedirect("Delete.jsp");
						} catch (DataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
					
					else
					{
						response.sendRedirect("search.jsp");
					}
				
				
				
	}

}
