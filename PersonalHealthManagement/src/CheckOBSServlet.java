

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

/**
 * Servlet implementation class CheckOBSServlet
 */
public class CheckOBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOBSServlet() {
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
		int patientSSn=Integer.parseInt(session.getAttribute("patientSSN").toString());
		
		if(request.getParameter("OSsubmit")!=null){
			
			Connection con=Dao.connect();
			String sql="select OS from OS,RECORDS where RECORDS.SSN=? and RECORDS.OBSID=OS.OBS_ID";
			try {
				Persons p=new Persons();
				p.setPerson_Id(patientSSn);
				
				
				ResultSet rs=Dao.checkOS(p, sql);
				
				if(rs.next()){
					getServletContext().getRequestDispatcher("/displayOSObs.jsp").forward(request, response);
				}else{
					request.setAttribute("msg", "No OS records..!!" );
					getServletContext().getRequestDispatcher("/norecords.jsp").forward(request, response);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(request.getParameter("BPsubmit")!=null){
			
			Connection con=Dao.connect();
			String sql="select * from BP,RECORDS where RECORDS.SSN=? and RECORDS.OBSID=BP.OBS_ID";
			try {
				Persons p=new Persons();
				p.setPerson_Id(patientSSn);
				
				
				ResultSet rs=Dao.checkBP(p, sql);
				
				if(rs.next()){
					getServletContext().getRequestDispatcher("/displayBPObs.jsp").forward(request, response);
				}else{
					request.setAttribute("msg", "No BP records..!!" );
					getServletContext().getRequestDispatcher("/norecords.jsp").forward(request, response);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
if(request.getParameter("Moodsubmit")!=null){
			
			Connection con=Dao.connect();
			String sql="select MOOD from MOOD,RECORDS where RECORDS.SSN=? and RECORDS.OBSID=MOOD.OBS_ID";
			try {
				Persons p=new Persons();
				p.setPerson_Id(patientSSn);
				
				
				ResultSet rs=Dao.checkMood(p, sql);
				
				if(rs.next()){
					getServletContext().getRequestDispatcher("/displayMoodObs.jsp").forward(request, response);
				}else{
					request.setAttribute("msg", "No Mood records..!!" );
					getServletContext().getRequestDispatcher("/norecords.jsp").forward(request, response);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}


if(request.getParameter("Painsubmit")!=null){
	
	Connection con=Dao.connect();
	String sql="select PAIN from PAIN,RECORDS where RECORDS.SSN=? and RECORDS.OBSID=PAIN.OBS_ID";
	try {
		Persons p=new Persons();
		p.setPerson_Id(patientSSn);
		
		
		ResultSet rs=Dao.checkPain(p, sql);
		
		if(rs.next()){
			getServletContext().getRequestDispatcher("/displayPainObs.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "No Pain records..!!" );
			getServletContext().getRequestDispatcher("/norecords.jsp").forward(request, response);
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}



if(request.getParameter("Weightsubmit")!=null){
	
	Connection con=Dao.connect();
	String sql="select WEIGHT from WEIGHT,RECORDS where RECORDS.SSN=? and RECORDS.OBSID=WEIGHT.OBS_ID";
	try {
		Persons p=new Persons();
		p.setPerson_Id(patientSSn);
		
		
		ResultSet rs=Dao.checkWeight(p, sql);
		
		if(rs.next()){
			getServletContext().getRequestDispatcher("/displayWeightObs.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "No Weight records..!!" );
			getServletContext().getRequestDispatcher("/norecords.jsp").forward(request, response);
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}



if(request.getParameter("Tempsubmit")!=null){
	
	Connection con=Dao.connect();
	String sql="select TEMP from TEMP,RECORDS where RECORDS.SSN=? and RECORDS.OBSID=TEMP.OBS_ID";
	try {
		Persons p=new Persons();
		p.setPerson_Id(patientSSn);
		
		
		ResultSet rs=Dao.checkOS(p, sql);
		
		if(rs.next()){
			getServletContext().getRequestDispatcher("/displayTempObs.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "No Temp records..!!" );
			getServletContext().getRequestDispatcher("/norecords.jsp").forward(request, response);
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
		
		
		
	}

}
