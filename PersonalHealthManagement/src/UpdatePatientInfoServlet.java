

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Persons;

/**
 * Servlet implementation class UpdatePatientInfoServlet
 */
public class UpdatePatientInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePatientInfoServlet() {
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
		int patientSSN=Integer.parseInt(session.getAttribute("patientSSN").toString());
		
		//update disease for sick
		if(request.getParameter("updateDisease")!=null){
			
			if(request.getParameter("diseaseSick").equals("") || request.getParameter("diseaseSickDiagnosed").equals("")){
				request.setAttribute("msg", "disease name or diagnosed date empty");
				getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").include(request, response);
			}
			else{
				String newDiseaseName=request.getParameter("diseaseSick");
				String newDiseaseDate=request.getParameter("diseaseSickDiagnosed");
				String diseasePrev=request.getParameter("diseaseSickPrev");
				
				String newDiseaseDateFinal =Dao.convertToSQLDate(newDiseaseDate);
				
				
				String sql="update SICKPERSON set DISEASE=?, DIAGNOSED=? where SSN=? and DISEASE=?";
				int updateDiseaseForSick=Dao.updateDiseaseForSick(patientSSN, newDiseaseName, newDiseaseDateFinal,diseasePrev, sql);
				
				if(updateDiseaseForSick>0){
					request.setAttribute("msg", "Succesfully updated for sick");
					getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
				}
				else{
					request.setAttribute("msg", "some problem");
					getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
				}
			
			
		}
		}
		
		
		
		
		
		
		//delete disease for sick
		if(request.getParameter("deleteDisease")!=null){
			String diseaseName=request.getParameter("diseaseSick");
			String diseaseDate=request.getParameter("diseaseSickDiagnosed");
			
			String sqlForDeleteDisease="delete from SICKPERSON where SSN=? and DISEASE=?";
			int deleteDisease=Dao.deleteDiseaseForSick(patientSSN, diseaseName, sqlForDeleteDisease);
			
			String checkForSickPatient="select * from SICKPERSON where SSN=?";
			ResultSet rs=Dao.checkForSickPatient(patientSSN, checkForSickPatient);
			
			try {
				if(!rs.next()){
					String insertIntoWellPerson="insert into WELLPERSON values(?)";
					
					int rs2=Dao.insertIntoWellPatient(patientSSN, insertIntoWellPerson);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(deleteDisease>0){
				request.setAttribute("msg", "Succesfully deleted for sick");
				getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
			}
			else{
				request.setAttribute("msg", "some problem");
				getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
			}
			
		}
		
		
		
		
		
		
		
		
		//add disease for sick
		if(request.getParameter("addDiseaseForSick")!=null){
			if(request.getParameter("diseaseNewSick").equals("") || request.getParameter("diseaseSickDiagnosedNew").equals("")){
				request.setAttribute("msg", "disease name or diagnosed date empty");
				getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").include(request, response);
			}else{
				String newDiseaseNameAdd=request.getParameter("diseaseNewSick");
				String newDiseaseDateAdd=request.getParameter("diseaseSickDiagnosedNew");
				
				String sql="insert into SICKPERSON values(?,?,?)";
				
				int rsnewDisForSick=Dao.insertIntoSickPatient(patientSSN, newDiseaseNameAdd, newDiseaseDateAdd, sql);
				
				if(rsnewDisForSick>0){
					request.setAttribute("msg", "Succesfully added disease for sick");
					getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
				}
				else{
					request.setAttribute("msg", "some problem");
					getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
				}
			}
		}
		
		
		
		
		
		
		
		//add disease for well
		if(request.getParameter("addDiseaseForWell")!=null){
			
			boolean noHScheck=false;
			
			if(request.getParameter("diseaseNewWell").equals("") || request.getParameter("diseaseWellDiagnosedNew").equals("")){
				request.setAttribute("msg", "disease name or diagnosed date empty");
				getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").include(request, response);
			}else if(!request.getParameter("noHSWell").equals("")){
				noHScheck=true;
				if(request.getParameter("wellNoHS").equals("") || request.getParameter("wellNoHSDOA").equals("")){
					request.setAttribute("msg", "HS SSN and DOA are required");
					getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").include(request, response);
				}
				
				else{
					String newDiseaseNameAddWell=request.getParameter("diseaseNewWell");
					String newDiseaseDateAddWell=request.getParameter("diseaseWellDiagnosedNew");
					int wellNoHSSSn=Integer.parseInt(request.getParameter("wellNoHS"));
					String wellNoHSDOA=request.getParameter("wellNoHSDOA");
					
					String sqldel="delete from WELLPERSON where SSN=?";
					String sqlins="insert into SICKPERSON values(?,?,?)";
					String addHS="insert into HEALTH_SUPPORTER values(?,?,?,?)";
					
					int ins=0,del=0;
					
					if(noHScheck){
						String sqlpers="select * from PERSONS where SSN=?";
						Persons p=new Persons();
						p.setPerson_Id(wellNoHSSSn);
						ResultSet rs=Dao.checkPerson(p, sqlpers);
						try {
							if(!rs.next()){
								request.setAttribute("msg", "Enter valid HS SSN");
								getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
							}
							else{
								 del=Dao.deleteWellPatient(patientSSN, sqldel);
								
								 ins=Dao.insertIntoSickPatient(patientSSN, newDiseaseNameAddWell, newDiseaseDateAddWell, sqlins);
								int hs=Dao.addHS( wellNoHSSSn,patientSSN, wellNoHSDOA, "primary", addHS);
								
								if(ins>0){
									request.setAttribute("msg", "Successfully updated");
									getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
								}
								else{
									request.setAttribute("msg", "some problem");
									getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						del=Dao.deleteWellPatient(patientSSN, sqldel);
						
						 ins=Dao.insertIntoSickPatient(patientSSN, newDiseaseNameAddWell, newDiseaseDateAddWell, sqlins);
						 
						 if(ins>0){
								request.setAttribute("msg", "Successfully updated");
								getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
							}
							else{
								request.setAttribute("msg", "some problem");
								getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
							}
					}
					
					
					
					
					
				}
				
			}
			else{
				String newDiseaseNameAddWell=request.getParameter("diseaseNewWell");
				String newDiseaseDateAddWell=request.getParameter("diseaseWellDiagnosedNew");
//				int wellNoHSSSn=Integer.parseInt(request.getParameter("wellNoHS"));
//				String wellNoHSDOA=request.getParameter("wellNoHSDOA");
				
				String sqldel="delete from WELLPERSON where SSN=?";
				String sqlins="insert into SICKPERSON values(?,?,?)";
				String addHS="insert into HEALTH_SUPPORTER values(?,?,?,?)";
				
				int ins=0,del=0;
				
//				if(noHScheck){
//					String sqlpers="select * from PERSONS where SSN=?";
//					Persons p=new Persons();
//					p.setPerson_Id(wellNoHSSSn);
//					ResultSet rs=Dao.checkPerson(p, sqlpers);
//					try {
//						if(!rs.next()){
//							request.setAttribute("msg", "Enter valid HS SSN");
//							getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
//						}
//						else{
//							 del=Dao.deleteWellPatient(patientSSN, sqldel);
//							
//							 ins=Dao.insertIntoSickPatient(patientSSN, newDiseaseNameAddWell, newDiseaseDateAddWell, sqlins);
//							int hs=Dao.addHS( wellNoHSSSn,patientSSN, wellNoHSDOA, "primary", addHS);
//							
//							if(ins>0){
//								request.setAttribute("msg", "Successfully updated");
//								getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
//							}
//							else{
//								request.setAttribute("msg", "some problem");
//								getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
//							}
//						}
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}else{
					del=Dao.deleteWellPatient(patientSSN, sqldel);
					
					 ins=Dao.insertIntoSickPatient(patientSSN, newDiseaseNameAddWell, newDiseaseDateAddWell, sqlins);
					 
					 if(ins>0){
							request.setAttribute("msg", "Successfully updated");
							getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
						}
						else{
							request.setAttribute("msg", "some problem");
							getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
						}
				
				
				
				
				
				
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		//update general info
		if(request.getParameter("UpdateGeneralInfo")!=null){
			if(request.getParameter("name").equals("") 
					|| request.getParameter("pass").equals("") 
					|| request.getParameter("address").equals("") 
					|| request.getParameter("dob").equals("") 
					|| request.getParameter("gender").equals("")){
				
				request.setAttribute("msg", "some info empty");
				getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").include(request, response);
				
			}else{
				String name=request.getParameter("name");
				String pass=request.getParameter("pass");
				String address=request.getParameter("address");
				String dob=request.getParameter("dob");
				
				String dobfinal=Dao.convertToSQLDate(dob);
				
				//System.out.println(dob);
				//String dobFinal =new String();
				//DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.MS", Locale.ENGLISH);
//				 try {
//					java.util.Date dobDate =  format.parse(dob);
//					SimpleDateFormat f=new SimpleDateFormat("dd-MMM-YY");
//					dobFinal = f.format(dobDate);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				String gender=request.getParameter("gender");
				
				String upd="update PERSONS set PASSWORD=?,NAME=?,ADDRESS=?,DOB=?,GENDER=? where SSN=?";
				
				int upres=Dao.updateGeneralInfo(patientSSN, name, pass, address, gender,dobfinal, upd);
				if(upres>0){
					request.setAttribute("msg", "Succesfully updated general info");
					getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
				}
				else{
					request.setAttribute("msg", "some problem");
					getServletContext().getRequestDispatcher("/updatePatientInfoHimself.jsp").forward(request, response);
				}
				
			}
		}
		
	


}

}