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
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Persons;
import model.SickPatient;
import model.WellPatient;

/**
 * Servlet implementation class RegisterPatientServlet
 */
public class RegisterPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at Vikas: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sql1="Insert into persons values(?,?,?,?,?,?)";
		String sql2="Insert into sickperson values(?,?,?)";
		String sql3="Insert into wellperson values(?)";
		
		HttpSession session=request.getSession();
		
		
		
		if(request.getParameter("person_id").equals("")
				|| request.getParameter("pass").equals("")
				|| request.getParameter("name").equals("")
				|| request.getParameter("address").equals("")
				|| request.getParameter("dob").equals("")
				|| request.getParameter("type")==null
				|| request.getParameter("gender")==null){
			
			request.setAttribute("msg", "some reqd info empty");
			getServletContext().getRequestDispatcher("/registerPatient.jsp").include(request, response);
		}
		
		else{
			
			
			
			String pID=request.getParameter("person_id");
			int pid=Integer.parseInt(pID);
			Persons p=new Persons();
			p.setPerson_Id(pid);
			
			session.setAttribute("patientSSN", pid);
			
			String check="select * from PERSONS where SSN=?";
			ResultSet rscheck=Dao.checkPerson(p, check);
			
			try {
				if(rscheck.next()){
					request.setAttribute("msg", "ssn alreay taken. enter diff ssn!");
					getServletContext().getRequestDispatcher("/registerPatient.jsp").include(request, response);
				}
				else{
					
					

					String password=request.getParameter("pass");
					String name=request.getParameter("name");
					String address=request.getParameter("address");
					String dob=request.getParameter("dob");
					String type=request.getParameter("type");
					String gender=request.getParameter("gender");
					
					
					
					Connection con=Dao.connect();
					ResultSet rs1=null;
					
					PreparedStatement ps1=null;
					PreparedStatement ps2=null;
					PreparedStatement ps3=null;
					
					
					//Setting persons data
							
							p.setName(name);
							p.setPassword(password);
							p.setAddress(address);
							p.setDob(dob);
							p.setGender(gender);
							
						
					
					if(type.equals("sick")){
						
						if(request.getParameter("hsssnval").equals("")
								|| request.getParameter("hsdoa").equals("")
								|| request.getParameter("disease").equals("")
								|| request.getParameter("dodd").equals("")){
							request.setAttribute("msg", "HS ,disease,diagnosed date and  HS DOA reqd for sick");
							getServletContext().getRequestDispatcher("/registerPatient.jsp").include(request, response);
						}
						else{

							String disease=request.getParameter("disease");
							String diagnosedDate=request.getParameter("dodd");
							int hsval=Integer.parseInt(request.getParameter("hsssnval"));
							String hsdoa=request.getParameter("hsdoa");
							SickPatient sp=new SickPatient();
							sp.setSsn(pid);
							sp.setDisease(disease);
							sp.setDiagnosedDate(diagnosedDate);
							Persons hp=new Persons();
							hp.setPerson_Id(hsval);
							check="select * from PERSONS where SSN=?";
							ResultSet rscheckHS=Dao.checkPerson(hp, check);
							
							if(!rscheckHS.next()){
								request.setAttribute("msg", "hs ssn not in persons");
								getServletContext().getRequestDispatcher("/registerPatient.jsp").forward(request, response);
							}
							else{
								
								
								try{
									if(con!=null){
										ps1=con.prepareStatement(sql1);
										ps2=con.prepareStatement(sql2);
										
										ps1.setInt(1, p.getPerson_Id());
										ps1.setString(2, p.getPassword());
										ps1.setString(3, p.getName());
										ps1.setString(4, p.getAddress());
										ps1.setString(5, p.getDob());
										ps1.setString(6, p.getGender());
										
										ps2.setInt(1, sp.getSsn());
										ps2.setString(2, sp.getDisease());
										ps2.setString(3, sp.getDiagnosedDate());
										
										int ip=ps1.executeUpdate();
										int isp=ps2.executeUpdate();
										
										String sql="insert into HEALTH_SUPPORTER values(?,?,?,?)";
										int uhs=Dao.addHS(hsval, pid, hsdoa, "primary", sql);
										
										
										if(ip>0 & isp>0){
											if(con!=null)
											{
											Dao.close(con);	
											}
											System.out.println("sick pat success");
											request.setAttribute("msg", "successfully added as sick");
											getServletContext().getRequestDispatcher("/patientmain.jsp").forward(request, response);
										}
										
									}
								}
								catch (SQLException e) {
									// TODO: handle exception
								}
							}
							
							
							
						}
						
						
						
					}
					else if(type.equals("well")){
						WellPatient wp=new WellPatient();
						wp.setSsn(pid);
						
						if(!(request.getParameter("disease").equals("") && request.getParameter("dodd").equals(""))){
							request.setAttribute("msg", "well pat cant have disease or disease diagnosed date");
							getServletContext().getRequestDispatcher("/registerPatient.jsp").include(request, response);
						}
						else{
							
							
							
							
							
							try{
								if(con!=null){
									ps1=con.prepareStatement(sql1);
									ps3=con.prepareStatement(sql3);
									
									ps1.setInt(1, p.getPerson_Id());
									ps1.setString(2, p.getPassword());
									ps1.setString(3, p.getName());
									ps1.setString(4, p.getAddress());
									ps1.setString(5, p.getDob());
									ps1.setString(6, p.getGender());
									
									ps3.setInt(1, wp.getSsn());
									
									
									int ip=ps1.executeUpdate();
									int iwp=ps3.executeUpdate();
									
									if(request.getParameter("hsssnval").equals("")
											|| request.getParameter("hsdoa").equals("")){
										
									}
									else{
										
										String sql="insert into HEALTH_SUPPORTER values(?,?,?,?)";
										int hsval=Integer.parseInt(request.getParameter("hsssnval"));
										String hsdoa=request.getParameter("hsdoa");
										int uhs=Dao.addHS(hsval, pid, hsdoa, "primary", sql);
									}
									
									if(ip>0 & iwp>0){
										if(con!=null)
										{
										Dao.close(con);	
										}
										System.out.println("well pat success");
										request.setAttribute("msg", "successfully added as well");
										getServletContext().getRequestDispatcher("/patientmain.jsp").forward(request, response);
									}
									
								}
							}
							catch (Exception e) {
								// TODO: handle exception
							}
							
							
							
						}
						
						
					}
					
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
		
		
		
	}

}
