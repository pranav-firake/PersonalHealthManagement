

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class HSToPatientPrev
 */
public class HSToPatientPrev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HSToPatientPrev() {
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
		
		HttpSession session=request.getSession();
		int hsSSN=Integer.parseInt(session.getAttribute("hssn").toString());
		
		if(request.getParameter("type")!=null){
			String type=request.getParameter("type");
			
			if(type.equalsIgnoreCase("sick")){
				getServletContext().getRequestDispatcher("/hsToSickPatient.jsp").forward(request, response);
			}
			
			
			
			if(type.equalsIgnoreCase("well")){
				String insertIntoWellPerson="insert into WELLPERSON values(?)";
				
				int rs2=Dao.insertIntoWellPatient(hsSSN, insertIntoWellPerson);
				
				if(rs2>0){
					request.setAttribute("msg", "Succesfully added to Well patient");
					getServletContext().getRequestDispatcher("/hsToPatient.jsp").forward(request, response);
				}
				else{
					request.setAttribute("msg", "some problem");
					getServletContext().getRequestDispatcher("/hsToPatient.jsp").forward(request, response);
				}
			}
			
		}
		
		
		else{
			request.setAttribute("msg", "select type");
			getServletContext().getRequestDispatcher("/hsToPatient.jsp").forward(request, response);
		}
		
	}

}
