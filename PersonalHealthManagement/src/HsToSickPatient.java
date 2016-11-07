

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Persons;

/**
 * Servlet implementation class HsToSickPatient
 */
public class HsToSickPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HsToSickPatient() {
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
		if(request.getParameter("disease").equals("") 
				|| request.getParameter("diseaseDate").equals("") 
				|| request.getParameter("hsSSNval").equals("") 
				|| request.getParameter("hsDOA").equals("") ){
			request.setAttribute("msg", "some info empty");
			getServletContext().getRequestDispatcher("/hsToSickPatient.jsp").include(request, response);
		}
		else{
			String disease=request.getParameter("disease");
			String diseaseDate=request.getParameter("diseaseDate");
			
			String hsSSNval=request.getParameter("hsSSNval");
			int hsval=Integer.parseInt(hsSSNval);
			String hsDOA=request.getParameter("hsDOA");
			
			Persons p=new Persons();
			p.setPerson_Id(Integer.parseInt(hsSSNval));
			
			String sql="select * from PERSONS where SSN=?";
			
			
			ResultSet rs=Dao.checkPerson(p, sql);
			
			try {
				if(rs.next()){
					String sqlins="insert into SICKPERSON values(?,?,?)";
					String sqlhs="insert into HEALTH_SUPPORTER VALUES(?,?,?,?)";
					int sick=Dao.insertIntoSickPatient(hsSSN, disease, diseaseDate, sqlins);
					int well=Dao.addHS(hsval, hsSSN, hsDOA, "primary", sqlhs);
					
					if(sick>0 && well>0){
						request.setAttribute("msg", "Succesfully added ");
						getServletContext().getRequestDispatcher("/hsToSickPatient.jsp").forward(request, response);
					}
					else{
						request.setAttribute("msg", "some problem ");
						getServletContext().getRequestDispatcher("/hsToSickPatient.jsp").forward(request, response);
					}
					
					
				}
				else{
					request.setAttribute("msg", "Enter valid ssn for hs ");
					getServletContext().getRequestDispatcher("/hsToSickPatient.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
