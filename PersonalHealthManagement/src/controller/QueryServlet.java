package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServlet
 */
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
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
		if(request.getParameter("qa")!=null){
			getServletContext().getRequestDispatcher("/qa.jsp").forward(request, response);
		}
		else if(request.getParameter("qb")!=null){
			getServletContext().getRequestDispatcher("/qb.jsp").forward(request, response);
	
		}else if(request.getParameter("qc")!=null){
			getServletContext().getRequestDispatcher("/qc.jsp").forward(request, response);
		}else if(request.getParameter("qd")!=null){
			getServletContext().getRequestDispatcher("/qd.jsp").forward(request, response);
		}else if(request.getParameter("qe")!=null){
			getServletContext().getRequestDispatcher("/qe.jsp").forward(request, response);
		}
	
	}

}
