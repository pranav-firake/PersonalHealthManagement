package controller;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		Connection con=Dao.connect();
		HttpSession session = request.getSession();
		
		if(request.getParameter("register")!=null){
			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		}
		else if(request.getParameter("submit")!=null){
			
			String pID=request.getParameter("person_id");
			String password=request.getParameter("pass");
			
			String cont=request.getParameter("persons");
			
			
			
			if(pID.equals(null)||pID==""||password.equals(null)||password=="")
			{
				if(con!=null)
				{
				Dao.close(con);	
				}
				request.setAttribute("msg", "All Fields are mandatory");
				getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
			
			}
			else if(request.getParameter("persons")==null){
				request.setAttribute("msg", "Select Type");
				getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
			}
			else{
				Persons p=new Persons();
				int pid=Integer.parseInt(pID);
				p.setPerson_Id(pid);
				p.setPassword(password);
				
				
				String sql="select * from persons where SSN=? and PASSWORD=?";
				
				ResultSet rs=Dao.loginPerson(p, sql);
				
				try {
					//Checking in Persons
					if(rs.next()){
						//Checking for patient
						if(cont.equals("p1")){
							session.setAttribute("pssn", pid);
							//System.out.println("Patient");
							sql="select * from sickperson where SSN=?";
							ResultSet rs1=Dao.loginSickPatient(p, sql);
							sql="select * from wellperson where SSN=?";
							ResultSet rs2=Dao.loginWellPatient(p, sql);
							//Checking for sick patient
							if(rs1.next()){
								session.setAttribute("patientSSN", pid);
								
								if(con!=null)
								{
								Dao.close(con);	
								}
								
								getServletContext().getRequestDispatcher("/patientmain.jsp").forward(request, response);
							}
							//Checking for well patient
							else if(rs2.next()){
								session.setAttribute("patientSSN", pid);
								
								if(con!=null)
								{
								Dao.close(con);	
								}
								getServletContext().getRequestDispatcher("/patientmain.jsp").forward(request, response);
							}
							else{
								if(con!=null)
								{
								Dao.close(con);	
								}
								request.setAttribute("msg", "You don't have authority to continue as Patient" );
								getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
							}
							
							
						}
						//Checking for health supporter
						else if(cont.equals("h1")){
							//System.out.println("Health Supporter");
							sql="select * from health_supporter where H_SSN=?";
							ResultSet rs2=Dao.loginHealthSupporter(p, sql);
							if(rs2.next()){
								if(con!=null)
								{
								Dao.close(con);	
								}
								session.setAttribute("hssn", p.getPerson_Id());
								getServletContext().getRequestDispatcher("/healthSuppMain.jsp").forward(request, response);
							}else{
								if(con!=null)
								{
								Dao.close(con);	
								}
								request.setAttribute("msg", "You don't have authority to continue as Health Supporter" );
								getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
							}
							
						}
						//Checking for doctor
						else if(cont.equals("d1")){
							sql="select * from health_supporter where DOCID=?";
							ResultSet rs3=Dao.loginHealthSupporter(p, sql);
							if(rs3.next()){
								if(con!=null)
								{
								Dao.close(con);	
								}
								getServletContext().getRequestDispatcher("/doctor.jsp").forward(request, response);
							}else{
								if(con!=null)
								{
								Dao.close(con);	
								}
								request.setAttribute("msg", "You don't have authority to continue as Doctor" );
								getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
							}
						}
						
						
					}
					//Invalid login
					else{
						if(con!=null)
						{
						Dao.close(con);	
						}
						request.setAttribute("msg", "ID or password not correct");
						getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(request.getParameter("run")!=null){
			getServletContext().getRequestDispatcher("/runQueries.jsp").forward(request, response);
		}
	
	}

}
