

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Persons;

/**
 * Servlet implementation class EnterObsByPatientsServlet
 */
public class EnterObsByPatientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterObsByPatientsServlet() {
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
		ArrayList<String> diseaseList=new ArrayList<>();
		HttpSession session=request.getSession();
		int patientSSN=Integer.parseInt(session.getAttribute("patientSSN").toString());
		Persons p=new Persons();
		p.setPerson_Id(patientSSN);
		int maxOBSID=0;
		int currentOBSID=0;
		
		ResultSet rsRecd=null;
		
		String sql="select * from SICKPERSON where SSN=?";
		ResultSet rs=Dao.getDiseaseList(p, sql);
		try{
			while(rs.next()){
				diseaseList.add(rs.getString("DISEASE"));
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
		String sqlagain="select * from SICKPERSON where SSN=?";
		ResultSet rswell=Dao.checkForSickPatient(patientSSN, sqlagain);
		
		
		System.out.println(diseaseList);
		if(request.getParameter("bpSys")=="" & 
				request.getParameter("bpDys")=="" & 
				request.getParameter("temp")=="" & 
				request.getParameter("weight")=="" & 
				request.getParameter("mood")=="" &
				request.getParameter("os")=="" & 
				request.getParameter("mood")=="" & 
				request.getParameter("pain")==""){
			
			request.setAttribute("msg", "All values empty" );
			getServletContext().getRequestDispatcher("/enterOBS.jsp").include(request, response);
		}
		else if(request.getParameter("obsDate").equals("")){
			request.setAttribute("msg", "OBS Date mandatory" );
			getServletContext().getRequestDispatcher("/enterOBS.jsp").forward(request, response);
		}
		else{

			String sqlCheckRecd="select * from RECORDS where OBSID=?";
			String sqlInsertRecord="insert into RECORDS values(?,?)";
			
			String insertBP="insert into BP(OBS_ID,BP_SYS,BP_DYS,OBS_DATE) values(?,?,?,?)";
			String insertWeight="insert into WEIGHT(OBS_ID,OBS_DATE,WEIGHT) values(?,?,?)";
			String insertTemp="insert into TEMP(OBS_ID,OBS_DATE,TEMP) values(?,?,?)";
			String insertMood="insert into MOOD(OBS_ID,OBS_DATE,MOOD) values(?,?,?)";
			String insertOS="insert into OS(OBS_ID,OBS_DATE,OS) values(?,?,?)";
			String insertPain="insert into PAIN(OBS_ID,OBS_DATE,PAIN) values(?,?,?)";
			
			String obsDate=request.getParameter("obsDate");
			
			
			
			String sqlMaxOBSID="select max(OBSID) as max from RECORDS";
			
				
				
					 maxOBSID=Dao.getRecordsMaxOBSID(sqlMaxOBSID);
				
				
				currentOBSID=maxOBSID+1;
				
			
			
			
			
			
			int resBP=0;
			int resMood=0;
			int resPain=0;
			int resTemp=0;
			int resWeight=0;
			int resOS=0;
			
			int res=0;
			
			boolean bp=false;
			boolean temp=false;
			boolean mood=false;
			boolean weight=false;
			boolean os=false;
			boolean pain=false;
			
			for(String s:diseaseList){
				s=s.trim();
				if(s.equals("HIV")){
					weight=true;
					bp=true;
					pain=true;
				}
				if(s.equals("HEART DISEASE")){
					weight=true;
					bp=true;
					mood=true;
				}
				if(s.equals("COPD")){
					os=true;
					temp=true;
				}
				
			}
			try {
				if(!rs.next()){
					weight=true;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			boolean checkBP=false;
			if(bp){
				if((request.getParameter("bpSys")!="" & request.getParameter("bpDys")!=""))
					{
						checkBP=true;
					}
				if((request.getParameter("bpSys")=="" & request.getParameter("bpDys")=="")){
					checkBP=true;
				}
			}
			boolean checkRecordFlag=true;
			if(bp==true & checkBP==false){
				request.setAttribute("msg", "Enter both BP SYS and DYS" );
				getServletContext().getRequestDispatcher("/enterOBS.jsp").forward(request, response);
			}else{
				
				//bp
				if(bp){
					if(!request.getParameter("bpSys").equals("") || !request.getParameter("bpDys").equals("")){
						
						
						
							rsRecd=Dao.checkRecords(currentOBSID, sqlCheckRecd);
							try {
								if(checkRecordFlag){
									 res=Dao.enterRecords(patientSSN, currentOBSID, sqlInsertRecord);
									 checkRecordFlag=false;
									
								}
								int bpSys=Integer.parseInt(request.getParameter("bpSys").toString());
								int bpDys=Integer.parseInt(request.getParameter("bpDys").toString());
								
								resBP=Dao.insertBP(currentOBSID, bpSys, bpDys, obsDate, insertBP);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						
						
						
						
					}
				
				}
				
				
				
				
				//mood
				if(mood){
					if(!request.getParameter("mood").equals("")){
						
						
							rsRecd=Dao.checkRecords(currentOBSID, sqlCheckRecd);
							try {
								if(checkRecordFlag){
									res=Dao.enterRecords(patientSSN, currentOBSID, sqlInsertRecord);
									checkRecordFlag=false;
								}
								
								String moodValue=request.getParameter("mood");
								
								resMood=Dao.insertMood(currentOBSID, moodValue, obsDate, insertMood);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						
						
					}
				}
				
				
				
				
				//os
				if(os){
					if(!request.getParameter("os").equals("")){
						
						
							
							
							
							rsRecd=Dao.checkRecords(currentOBSID, sqlCheckRecd);
							try {
								if(checkRecordFlag){
									 res=Dao.enterRecords(patientSSN, currentOBSID, sqlInsertRecord);
									checkRecordFlag=false;
								}
								
								int osValue=Integer.parseInt(request.getParameter("os").toString());
								
								resOS=Dao.insertOS(currentOBSID, osValue, obsDate, insertOS);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						
						
					}
				}
				
				
				
				//pain
				if(pain){
					if(!request.getParameter("pain").equals("")){
						
						
							
								rsRecd=Dao.checkRecords(currentOBSID, sqlCheckRecd);
								try {
									if(checkRecordFlag){
										 res=Dao.enterRecords(patientSSN, currentOBSID, sqlInsertRecord);
										checkRecordFlag=false;
									}
									
									int painValue=Integer.parseInt(request.getParameter("pain").toString());
									
									resPain=Dao.insertPain(currentOBSID, painValue, obsDate, insertPain);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
							
							
						
						
						
						
					}
				}
				
				
				//temp
						if(temp){
							if(!request.getParameter("temp").equals("")){
								
									
									
										rsRecd=Dao.checkRecords(currentOBSID, sqlCheckRecd);
										try {
											if(checkRecordFlag){
												 res=Dao.enterRecords(patientSSN, currentOBSID, sqlInsertRecord);
												checkRecordFlag=false;
											}
											
											int tempValue=Integer.parseInt(request.getParameter("temp").toString());
											
											resTemp=Dao.insertTemp(currentOBSID, tempValue, obsDate, insertTemp);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									
									
								
							}
							
							
						}
				
				
						
						
						
						
						//weight
						if(weight){
							
							if(!request.getParameter("weight").equals("")){
								

									
										rsRecd=Dao.checkRecords(currentOBSID, sqlCheckRecd);
										try {
											if(checkRecordFlag){
												 res=Dao.enterRecords(patientSSN, currentOBSID, sqlInsertRecord);
												checkRecordFlag=false;
											}
											
											int weightValue=Integer.parseInt(request.getParameter("weight").toString());
											
											resWeight=Dao.insertWeight(currentOBSID, weightValue, obsDate, insertWeight);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									
								}
							

							
							
							
						}	
						
						
						if((resBP > 0 || resMood > 0 || resOS> 0 || resPain>0 || resTemp>0 || resWeight>0) & res>0){
							request.setAttribute("msg", "Successfully updated" );
							
							getServletContext().getRequestDispatcher("/enterOBS.jsp").forward(request, response);
						}else{
							request.setAttribute("msg", "Enter Relevant Data!" );
							
							getServletContext().getRequestDispatcher("/enterOBS.jsp").include(request, response);
						}
			
				
			}
			
		
			
		
	
		
		}
		
				
				
		
	}

}
