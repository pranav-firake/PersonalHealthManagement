package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.HealthSupporters;
import model.Persons;

/**
 * Servlet implementation class RegisterHS
 */
public class RegisterHS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterHS() {
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
		
		String pID=request.getParameter("person_id");
		String password=request.getParameter("pass");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String dob=request.getParameter("dob");
		String type=request.getParameter("type");
		String gender=request.getParameter("gender");
		String patientSSN=request.getParameter("patientSSN");
		String doa=request.getParameter("doa");
		
		
		Connection con=Dao.connect();
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps=null;
		
		int pid=Integer.parseInt(pID);
		int patientSSn=Integer.parseInt(patientSSN);
		
		//Setting persons data
		Persons p=new Persons();
		p.setPerson_Id(pid);
		p.setName(name);
		p.setPassword(password);
		p.setAddress(address);
		p.setDob(dob);
		p.setGender(gender);
		
		//setting health supporters data
		int patientssn=Integer.parseInt(patientSSN);
		HealthSupporters hs=new HealthSupporters();
		hs.setHssn(pid);
		hs.setPatient_ssn(patientssn);
		hs.setType(type);
		hs.setHname(name);
		hs.setDoa(doa);
		
		String sql="select * from persons where SSN=?";
		String sql1="Insert into persons values(?,?,?,?,?,?)";
		String sql2="Insert into health_supporter values(?,?,?,?,?)";
		
		
		try {
			if(con!=null){
				
				ps=con.prepareStatement(sql);
				int check=ps.executeUpdate();
				
				if(check > 0){
					System.out.println("correct patient ssn");
					ps1=con.prepareStatement(sql1);
					ps2=con.prepareStatement(sql2);
					
					ps1.setInt(1, p.getPerson_Id());
					ps1.setString(2, p.getPassword());
					ps1.setString(3, p.getName());
					ps1.setString(4, p.getAddress());
					ps1.setString(5, p.getDob());
					ps1.setString(6, p.getGender());
					
					int ip=ps1.executeUpdate();
					
					ps2.setInt(1, hs.getHssn());
					ps2.setInt(2,hs.getPatient_ssn());
					ps2.setString(3, hs.getHname());
					ps2.setString(4, hs.getDoa());
					ps2.setString(5, hs.getType());
					
					int ihs=ps2.executeUpdate();
					
					if(ip >0 & ihs>0){
						System.out.println("successfully ");
						if(con!=null)
						{
						Dao.close(con);	
						}
						getServletContext().getRequestDispatcher("/healthsupporter.jsp").forward(request, response);
					}
					
				}
				else{
					System.out.println("incorrect patiet ssn");
					if(con!=null)
					{
					Dao.close(con);	
					}
					request.setAttribute("msg", "Invaid Patient SSN" );
					getServletContext().getRequestDispatcher("/registerHS.jsp").include(request, response);
				}
				
				
				
				
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
