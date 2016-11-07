package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		// TODO Auto-generated method stub
		
		//Connection con=Dao.connect();
		
		
		
		
		if(request.getParameter("continue")!=null){
			String cont=request.getParameter("persons");
			
			//selection empty
			if(!(cont.equals("p1") || cont.equals("h1") )){
				request.setAttribute("msg", "Select one" );
				getServletContext().getRequestDispatcher("/register.jsp").include(request, response);
			}
			else{
				//patient
				if(cont.equals("p1")){
					getServletContext().getRequestDispatcher("/registerPatient.jsp").forward(request, response);
				}
				//health supp
				else if(cont.equals("h1")){
					getServletContext().getRequestDispatcher("/registerHS.jsp").forward(request, response);
				}
				
			}
			
			
		}
	}

}
