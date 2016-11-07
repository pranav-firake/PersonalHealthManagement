

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
import javax.websocket.Session;

import dao.Dao;
import model.Persons;

/**
 * Servlet implementation class ManageHealthSupportersPatientServlet
 */
public class ManageHealthSupportersPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageHealthSupportersPatientServlet() {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		HttpSession session = request.getSession();
		
		//Action for Edit/Update of primary Health Supporter
		if(request.getParameter("editADDPrimaryHS")!=null){
			
			
			
			
			//update primary Health supp
			if(request.getParameter("primaryHSOriginal")!=null){
				
				if(request.getParameter("primaryHSNew").equals("")){
					request.setAttribute("msg", "Health Supporter SSN empty" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}
				else if(request.getParameter("doaPrimary").equals("")){
					request.setAttribute("msg", "Date of Authorization empty..!!" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}
				else{
					//con=Dao.connect();
					String updatedHSSSN=request.getParameter("primaryHSNew");
					int newHSpid=Integer.parseInt(updatedHSSSN);
					Persons p=new Persons();
					p.setPerson_Id(newHSpid);
					String sql="select * from persons where SSN=?";
					ResultSet rs1=Dao.checkPerson(p, sql);
					try{
						//Valid ssn
						if(rs1.next()){
							String ownSSN=session.getAttribute("patientSSN").toString();
							int ownssn=Integer.parseInt(session.getAttribute("patientSSN").toString());
							String primaryHSSSN=request.getParameter("primaryHSOriginal");
							int primaryHSPrev=Integer.parseInt(primaryHSSSN);
							if(request.getParameter("secondaryHSOriginal")!=null){
								String secondHSSSN=request.getParameter("secondaryHSOriginal");
								
								if(!(ownSSN.equals(updatedHSSSN) || primaryHSSSN.equals(updatedHSSSN) || secondHSSSN.equals(updatedHSSSN))){
									String sql1="update HEALTH_SUPPORTER set H_SSN=?, DOA=? where P_SSN=? and H_SSN=?";
									String doa=request.getParameter("doaPrimary");
									int result=Dao.updateHS(newHSpid, ownssn, doa, primaryHSPrev, sql1);
									
									if(result > 0){
										request.setAttribute("msg", "Successfully updated" );
										if(con!=null)
										{
										Dao.close(con);	
										}
										getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").forward(request, response);
									}
								}
								else{
									request.setAttribute("msg", "Enter proper SSN for new HS" );
									if(con!=null)
									{
									Dao.close(con);	
									}
									getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
								}
								
							}
							else{
								if(!(ownSSN.equals(updatedHSSSN) || primaryHSSSN.equals(updatedHSSSN) )){
									String sql1="update HEALTH_SUPPORTER set H_SSN=?, DOA=? where P_SSN=? and H_SSN=?";
									String doa=request.getParameter("doaPrimary");
									int result=Dao.updateHS(newHSpid, ownssn, doa, primaryHSPrev, sql1);
									
									if(result > 0){
										System.out.println("success");
										request.setAttribute("msg", "Successfully updated" );
										if(con!=null)
										{
										Dao.close(con);	
										}
										getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").forward(request, response);
									}
								}
								else{
									request.setAttribute("msg", "Enter proper SSN for new HS" );
									if(con!=null)
									{
									Dao.close(con);	
									}
									getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
								}
							}
						}
						//invalid ssn
						else{
							request.setAttribute("msg", "Enter valid SSN. Entered SSN for HS does nott exists" );
							if(con!=null)
							{
							Dao.close(con);	
							}
							getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
						}
					}
					catch (SQLException e) {
						// TODO: handle exception
					}
				}
				
		
				
			}
			
			//add health supp
			else{
				
				if(request.getParameter("primaryHSNew").equals("")){
					request.setAttribute("msg", "HS SSN empty" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}
				else if(request.getParameter("doaPrimary").equals("")){
					request.setAttribute("msg", "Date of Authorization empty..!!" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}else{
					String updatedHSSSN=request.getParameter("primaryHSNew");
					int newHSpid=Integer.parseInt(updatedHSSSN);
					Persons p=new Persons();
					p.setPerson_Id(newHSpid);
					String sql="select * from persons where SSN=?";
					ResultSet rs1=Dao.checkPerson(p, sql);
					
					try{
						//valid ssn
						if(rs1.next()){
							String ownSSN=session.getAttribute("patientSSN").toString();
							int ownssn=Integer.parseInt(session.getAttribute("patientSSN").toString());
							
							if(!(ownSSN.equals(updatedHSSSN))){
								String addsql="Insert into health_supporter values(?,?,?,?)";
								String doa=request.getParameter("doaPrimary");
								String type="primary";
								int result=Dao.addHS(newHSpid, ownssn, doa, type, addsql);
								if(result>0){
									request.setAttribute("msg", "Successfully added new HS" );
									if(con!=null)
									{
									Dao.close(con);	
									}
									getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").forward(request, response);
								}
							}
							else{
								request.setAttribute("msg", "Enter proper SSN for new HS" );
								if(con!=null)
								{
								Dao.close(con);	
								}
								getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
							}
						}
						//invalid ssn
						else{
							request.setAttribute("msg", "Enter valid SSN. Entered SSN for HS does nott exists" );
							if(con!=null)
							{
							Dao.close(con);	
							}
							getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
						}
					}catch (SQLException e) {
						// TODO: handle exception
					}
				}
				
				
				
			}
				
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//delete of Primary HS
		else if(request.getParameter("deletePrimaryHS")!=null){
			//con=Dao.connect();
			HttpSession session1 = request.getSession();
			
			//String secondaryHS=request.getParameter("secondaryHSOriginal");
			
			//no secondary HS
			if(!(request.getParameter("secondaryHSOriginal") != null)){
				try{
					
					int ownSSn=Integer.parseInt(session1.getAttribute("patientSSN").toString());
					String sql3="select * from sickperson where SSN=?";
					Persons p=new Persons();
					p.setPerson_Id(ownSSn);
					
					
					ResultSet rs=Dao.loginSickPatient(p, sql3);
					//person sick.. so can't delete primary HS
					if(rs.next()){
						request.setAttribute("msg", "You are Sick. You can't delete your Primary HS" );
						if(con!=null)
						{
						Dao.close(con);	
						}
						getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
					}
					else{
						int primaryHSprev=Integer.parseInt(request.getParameter("primaryHSOriginal").toString());
						sql3="delete from health_supporter where H_SSN=? and P_SSN=?";
						int res=Dao.deleteHS(primaryHSprev, ownSSn, sql3);
						
						
						
						
						if(res>0){
							request.setAttribute("msg", "Successfully deleted HS" );
							if(con!=null)
							{
							Dao.close(con);	
							}
							getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").forward(request, response);
						}
					}
				}
				catch (SQLException e) {
					// TODO: handle exception
				}
				
				
			}
			else{
				request.setAttribute("msg", "You can't delete Primary HS before Secondary HS" );
				
				getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		//Edit update secondary HSS
		else if(request.getParameter("editAddSecondaryHS")!=null){

			//update secondary Health supp
			if(request.getParameter("secondaryHSOriginal")!=null){
				
				if(request.getParameter("secondaryHSNew").equals("")){
					request.setAttribute("msg", "HS SSN empty" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}
				else if(request.getParameter("doaSecondary").equals("")){
					request.setAttribute("msg", "Enter DOA" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}
				else{
					//con=Dao.connect();
					String updatedHSSSN=request.getParameter("secondaryHSNew");
					int newHSpid=Integer.parseInt(updatedHSSSN);
					Persons p=new Persons();
					p.setPerson_Id(newHSpid);
					String sql="select * from persons where SSN=?";
					ResultSet rs1=Dao.checkPerson(p, sql);
					try{
						//Valid ssn
						if(rs1.next()){
							String ownSSN=session.getAttribute("patientSSN").toString();
							int ownssn=Integer.parseInt(session.getAttribute("patientSSN").toString());
							String secondaryHSSSN=request.getParameter("secondaryHSOriginal");
							String primaryHSSN=request.getParameter("primaryHSOriginal");
							int primaryPrev=Integer.parseInt(primaryHSSN);
						
								int secondHSSSN=Integer.parseInt(secondaryHSSSN);
								
								if(!(ownSSN.equals(updatedHSSSN) || primaryHSSN.equals(updatedHSSSN) || secondaryHSSSN.equals(updatedHSSSN))){
									String sql1="update HEALTH_SUPPORTER set H_SSN=?, DOA=? where P_SSN=? and H_SSN=?";
									String doa=request.getParameter("doaSecondary");
									int result=Dao.updateHS(newHSpid, ownssn, doa, secondHSSSN, sql1);
									
									if(result > 0){
										request.setAttribute("msg", "Successfully updated" );
										if(con!=null)
										{
										Dao.close(con);	
										}
										getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").forward(request, response);
									}
								}
								else{
									request.setAttribute("msg", "Enter proper SSN for new HS" );
									if(con!=null)
									{
									Dao.close(con);	
									}
									getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
								}
								
							}
							
						
						//invalid ssn
						else{
							request.setAttribute("msg", "Enter valid SSN. Entered SSN for HS does nott exists" );
							if(con!=null)
							{
							Dao.close(con);	
							}
							getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
						}
					}
					catch (SQLException e) {
						// TODO: handle exception
					}
				}
				
				
				
			}
			
			//add health supp
			else{
				
				if(request.getParameter("secondaryHSNew").equals("")){
					request.setAttribute("msg", "HS SSN empty" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}
				else if(request.getParameter("doaSecondary").equals("")){
					request.setAttribute("msg", "Enter DOA" );
					if(con!=null)
					{
					Dao.close(con);	
					}
					getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
				}
				else{

					//con=Dao.connect();
					String updatedHSSSN=request.getParameter("secondaryHSNew");
					int newHSpid=Integer.parseInt(updatedHSSSN);
					Persons p=new Persons();
					p.setPerson_Id(newHSpid);
					String sql="select * from persons where SSN=?";
					ResultSet rs1=Dao.checkPerson(p, sql);
					String primaryHSSN=request.getParameter("primaryHSOriginal");
					
					try{
						//valid ssn
						if(rs1.next()){
							String ownSSN=session.getAttribute("patientSSN").toString();
							int ownssn=Integer.parseInt(session.getAttribute("patientSSN").toString());
							
							if(!(ownSSN.equals(updatedHSSSN) || primaryHSSN.equals(updatedHSSSN)  )){
								String addsql="Insert into health_supporter values(?,?,?,?)";
								String doa=request.getParameter("doaSecondary");
								String type="secondary";
								int result=Dao.addHS(newHSpid, ownssn, doa, type, addsql);
								if(result>0){
									request.setAttribute("msg", "Successfully added new HS" );
									if(con!=null)
									{
									Dao.close(con);	
									}
									getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").forward(request, response);
								}
							}
							else{
								request.setAttribute("msg", "Enter proper SSN for new HS" );
								if(con!=null)
								{
									Dao.close(con);	
								}
								getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
							}
						}
						//invalid ssn
						else{
							request.setAttribute("msg", "Enter valid SSN. Entered SSN for HS does nott exists" );
							if(con!=null)
							{
							Dao.close(con);	
							}
							getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").include(request, response);
						}
					}catch (SQLException e) {
						// TODO: handle exception
					}
				}
				
				
				
			}
				
		
			
		}
		
		
		
		
		
		
		
		//delete of secondary HS
		else if(request.getParameter("deleteSecondaryHS")!=null){
			//con=Dao.connect();
			HttpSession session1 = request.getSession();
			
			int ownSSn=Integer.parseInt(session1.getAttribute("patientSSN").toString());
			
			int secondaryHSssnPrev=Integer.parseInt(request.getParameter("secondaryHSOriginal").toString());
			try{
				String sql3="delete from health_supporter where H_SSN=? and P_SSN=?";
				int res=Dao.deleteHS(secondaryHSssnPrev, ownSSn, sql3);
				
				if(res>0){
					request.setAttribute("msg", "Successfully deleted HS" );
				if(con!=null)
				{
				Dao.close(con);	
				}
				getServletContext().getRequestDispatcher("/manageHSForPatients.jsp").forward(request, response);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			

			
			
	}
	
		
		
		

}
}