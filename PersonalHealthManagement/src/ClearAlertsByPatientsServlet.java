

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

/**
 * Servlet implementation class ClearAlertsByPatientsServlet
 */
public class ClearAlertsByPatientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearAlertsByPatientsServlet() {
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
		int alertID=Integer.parseInt(request.getParameter("alertID").toString());
		System.out.println(alertID);
		
		String sql="Delete from ALERT where ALERTID=?";
		int res=Dao.deleteAlert(alertID, sql);
		
		if(res>0){
			request.setAttribute("msg", "Alert Successfully Deleted" );
			getServletContext().getRequestDispatcher("/alertsPatients.jsp").forward(request, response);
		}
	}

}
