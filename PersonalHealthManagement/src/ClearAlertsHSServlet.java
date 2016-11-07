

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class ClearAlertsHSServlet
 */
public class ClearAlertsHSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearAlertsHSServlet() {
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
		
		int pid=Integer.parseInt(request.getParameter("pssn"));
		System.out.println(pid);
		
		HttpSession session=request.getSession();
		session.setAttribute("patientSSNAlert", pid);
		
		if(request.getParameter("submit")!=null){
			getServletContext().getRequestDispatcher("/deleteAlertsByHS.jsp").forward(request, response);
		}
		
		else if(request.getParameter("viewPatInfo")!=null){
			getServletContext().getRequestDispatcher("/viewPatientInfoForHS.jsp").forward(request, response);
		}
		
		else if(request.getParameter("editPatReco")!=null){
			getServletContext().getRequestDispatcher("/editPatientReco.jsp").forward(request, response);
		}
	}

}
