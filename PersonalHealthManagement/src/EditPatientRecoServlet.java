

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class EditPatientRecoServlet
 */
public class EditPatientRecoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPatientRecoServlet() {
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
		int patientSSn=Integer.parseInt(session.getAttribute("patientSSNAlert").toString());
		
		if(request.getParameter("edit")!=null){
			
			Integer weightFreq=0;
			Integer Weight_Low=0;
			Integer Weight_high=0;
			Integer BP_FREQ=0;
			Integer BP_SYS_LOW=0;
			Integer BP_SYS_HIGH=0;
			Integer BP_DYS_LOW=0;
			Integer BP_DYS_HIGH=0;
			Integer OS_FREQ=0;
			Integer OS_LOW=0;
			Integer OS_HIGH=0;
			Integer PAIN_FREQ=0;
			Integer PAIN_HIGH=0;
			Integer MOOD_FREQ=0;
			Integer TEMP_FREQ=0;
			Integer TEMP_LOW=0;
			Integer TEMP_HIGH=0;
			
			String MOOD_VAL="";
			
			
			if(request.getParameter("Weight_Freq").equals("")){
				 //weightFreq=Integer.parseInt("");
			}
			else{
				 weightFreq=Integer.parseInt(request.getParameter("Weight_Freq"));
			}
			
			
			
			if(request.getParameter("Weight_Low").equals("")){
				//Weight_Low=Integer.parseInt("");
			}
			else{
				Weight_Low=Integer.parseInt(request.getParameter("Weight_Low"));
			}
			
			
			
			if(request.getParameter("Weight_high").equals("")){
				 //Weight_high=Integer.parseInt("");
			}
			else{
				Weight_high=Integer.parseInt(request.getParameter("Weight_high"));
			}
			
			
			
			if(request.getParameter("BP_FREQ").equals("")){
				//BP_FREQ=Integer.parseInt("");
			}
			else{
				 BP_FREQ=Integer.parseInt(request.getParameter("BP_FREQ"));
			}
			
			
			if(request.getParameter("BP_SYS_LOW").equals("")){
				//BP_SYS_LOW=Integer.parseInt("");
			}
			else{
				BP_SYS_LOW=Integer.parseInt(request.getParameter("BP_SYS_LOW"));
			}
			
			
			
			if(request.getParameter("BP_SYS_HIGH").equals("")){
				//BP_SYS_HIGH=Integer.parseInt("");
			}
			else{
				BP_SYS_HIGH=Integer.parseInt(request.getParameter("BP_SYS_HIGH"));
			}
			
			
			
			if(request.getParameter("BP_DYS_LOW").equals("")){
				//BP_DYS_LOW=Integer.parseInt("");
			}
			else{
				BP_DYS_LOW=Integer.parseInt(request.getParameter("BP_DYS_LOW"));
			}
			
			
			if(request.getParameter("BP_DYS_HIGH").equals("")){
				//BP_DYS_HIGH=Integer.parseInt("");;
			}
			else{
				BP_DYS_HIGH=Integer.parseInt(request.getParameter("BP_DYS_HIGH"));
			}
			
			
			if(request.getParameter("OS_FREQ").equals("")){
				 //OS_FREQ=Integer.parseInt("");
			}
			else{
				 OS_FREQ=Integer.parseInt(request.getParameter("OS_FREQ"));
			}
			
			
			if(request.getParameter("OS_LOW").equals("")){
				//OS_LOW=Integer.parseInt("");
			}
			else{
				 OS_LOW=Integer.parseInt(request.getParameter("OS_LOW"));
			}
			
			
			if(request.getParameter("OS_HIGH").equals("")){
				//OS_HIGH=Integer.parseInt("");
			}
			else{
				OS_HIGH=Integer.parseInt(request.getParameter("OS_HIGH"));
			}
			
			
			if(request.getParameter("PAIN_FREQ").equals("")){
				//PAIN_FREQ=Integer.parseInt("");
			}
			else{
				PAIN_FREQ=Integer.parseInt(request.getParameter("PAIN_FREQ"));
			}
			
			
			if(request.getParameter("PAIN_HIGH").equals("")){
				//PAIN_HIGH=Integer.parseInt("");
			}
			else{
				PAIN_HIGH=Integer.parseInt(request.getParameter("PAIN_HIGH"));
			}
			
			
			if(request.getParameter("MOOD_FREQ").equals("")){
				 //MOOD_FREQ=Integer.parseInt("");
			}
			else{
				 MOOD_FREQ=Integer.parseInt(request.getParameter("MOOD_FREQ"));
			}
			
			
			if(request.getParameter("MOOD_VAL").equals("")){
				 //MOOD_VAL="";
			}
			else{
				MOOD_VAL=request.getParameter("MOOD_VAL");
			}
			
			
			if(request.getParameter("TEMP_FREQ").equals("")){
				//TEMP_FREQ=Integer.parseInt("");
			}
			else{
				TEMP_FREQ=Integer.parseInt(request.getParameter("TEMP_FREQ"));
			}
			
			if(request.getParameter("TEMP_LOW").equals("")){
				//TEMP_LOW=Integer.parseInt("");
			}
			else{
				 TEMP_LOW=Integer.parseInt(request.getParameter("TEMP_LOW"));
			}
			
			
			if(request.getParameter("TEMP_HIGH").equals("")){
				 //TEMP_HIGH=Integer.parseInt("");
			}
			else{
				TEMP_HIGH=Integer.parseInt(request.getParameter("TEMP_HIGH"));
			}
			
			
			
			String sql="update SPCL_PATIENT_RECO set WEIGHT_FREQ=?,WEIGHT_LOW=?,WEIGHT_HIGH=?, BP_FREQ=?,"
					+ "BP_SYS_LOW=?, BP_SYS_HIGH=?, BP_DYS_LOW=?, BP_DYS_HIGH=?, OS_FREQ=?, OS_LOW=?,OS_HIGH=?,PAIN_FREQ=?, PAIN_HIGH=?,"
					+ " MOOD_FREQ=?, MOOD_VAL=?,TEMP_FREQ=?,TEMP_LOW=?, TEMP_HIGH=? WHERE SSN=?";
			int res=Dao.editIntoSpclPatientReco(patientSSn, weightFreq, Weight_Low, Weight_high, BP_FREQ, BP_SYS_LOW, BP_SYS_HIGH, BP_DYS_LOW, 
					BP_DYS_HIGH, OS_FREQ, OS_LOW, OS_HIGH, PAIN_FREQ, PAIN_HIGH, MOOD_FREQ, MOOD_VAL, TEMP_FREQ, TEMP_LOW, TEMP_HIGH, sql);
			if(res>0){
				request.setAttribute("msg", "updated successfully");
				getServletContext().getRequestDispatcher("/editPatientReco.jsp").include(request, response);
			}
			else{
				request.setAttribute("msg", "some problem");
				getServletContext().getRequestDispatcher("/editPatientReco.jsp").include(request, response);
			}
			
		}
			
		
		
		
		
		
		
		
		
		if(request.getParameter("submit")!=null){
			Integer weightFreq=0;
			Integer Weight_Low=0;
			Integer Weight_high=0;
			Integer BP_FREQ=0;
			Integer BP_SYS_LOW=0;
			Integer BP_SYS_HIGH=0;
			Integer BP_DYS_LOW=0;
			Integer BP_DYS_HIGH=0;
			Integer OS_FREQ=0;
			Integer OS_LOW=0;
			Integer OS_HIGH=0;
			Integer PAIN_FREQ=0;
			Integer PAIN_HIGH=0;
			Integer MOOD_FREQ=0;
			Integer TEMP_FREQ=0;
			Integer TEMP_LOW=0;
			Integer TEMP_HIGH=0;
			
			String MOOD_VAL="";
			
			
			if(request.getParameter("Weight_Freq").equals("")){
				 //weightFreq=Integer.parseInt("");
			}
			else{
				 weightFreq=Integer.parseInt(request.getParameter("Weight_Freq"));
			}
			
			
			
			if(request.getParameter("Weight_Low").equals("")){
				//Weight_Low=Integer.parseInt("");
			}
			else{
				Weight_Low=Integer.parseInt(request.getParameter("Weight_Low"));
			}
			
			
			
			if(request.getParameter("Weight_high").equals("")){
				 //Weight_high=Integer.parseInt("");
			}
			else{
				Weight_high=Integer.parseInt(request.getParameter("Weight_high"));
			}
			
			
			
			if(request.getParameter("BP_FREQ").equals("")){
				//BP_FREQ=Integer.parseInt("");
			}
			else{
				 BP_FREQ=Integer.parseInt(request.getParameter("BP_FREQ"));
			}
			
			
			if(request.getParameter("BP_SYS_LOW").equals("")){
				//BP_SYS_LOW=Integer.parseInt("");
			}
			else{
				BP_SYS_LOW=Integer.parseInt(request.getParameter("BP_SYS_LOW"));
			}
			
			
			
			if(request.getParameter("BP_SYS_HIGH").equals("")){
				//BP_SYS_HIGH=Integer.parseInt("");
			}
			else{
				BP_SYS_HIGH=Integer.parseInt(request.getParameter("BP_SYS_HIGH"));
			}
			
			
			
			if(request.getParameter("BP_DYS_LOW").equals("")){
				//BP_DYS_LOW=Integer.parseInt("");
			}
			else{
				BP_DYS_LOW=Integer.parseInt(request.getParameter("BP_DYS_LOW"));
			}
			
			
			if(request.getParameter("BP_DYS_HIGH").equals("")){
				//BP_DYS_HIGH=Integer.parseInt("");;
			}
			else{
				BP_DYS_HIGH=Integer.parseInt(request.getParameter("BP_DYS_HIGH"));
			}
			
			
			if(request.getParameter("OS_FREQ").equals("")){
				 //OS_FREQ=Integer.parseInt("");
			}
			else{
				 OS_FREQ=Integer.parseInt(request.getParameter("OS_FREQ"));
			}
			
			
			if(request.getParameter("OS_LOW").equals("")){
				//OS_LOW=Integer.parseInt("");
			}
			else{
				 OS_LOW=Integer.parseInt(request.getParameter("OS_LOW"));
			}
			
			
			if(request.getParameter("OS_HIGH").equals("")){
				//OS_HIGH=Integer.parseInt("");
			}
			else{
				OS_HIGH=Integer.parseInt(request.getParameter("OS_HIGH"));
			}
			
			
			if(request.getParameter("PAIN_FREQ").equals("")){
				//PAIN_FREQ=Integer.parseInt("");
			}
			else{
				PAIN_FREQ=Integer.parseInt(request.getParameter("PAIN_FREQ"));
			}
			
			
			if(request.getParameter("PAIN_HIGH").equals("")){
				//PAIN_HIGH=Integer.parseInt("");
			}
			else{
				PAIN_HIGH=Integer.parseInt(request.getParameter("PAIN_HIGH"));
			}
			
			
			if(request.getParameter("MOOD_FREQ").equals("")){
				 //MOOD_FREQ=Integer.parseInt("");
			}
			else{
				 MOOD_FREQ=Integer.parseInt(request.getParameter("MOOD_FREQ"));
			}
			
			
			if(request.getParameter("MOOD_VAL").equals("")){
				 //MOOD_VAL="";
			}
			else{
				MOOD_VAL=request.getParameter("MOOD_VAL");
			}
			
			
			if(request.getParameter("TEMP_FREQ").equals("")){
				//TEMP_FREQ=Integer.parseInt("");
			}
			else{
				TEMP_FREQ=Integer.parseInt(request.getParameter("TEMP_FREQ"));
			}
			
			if(request.getParameter("TEMP_LOW").equals("")){
				//TEMP_LOW=Integer.parseInt("");
			}
			else{
				 TEMP_LOW=Integer.parseInt(request.getParameter("TEMP_LOW"));
			}
			
			
			if(request.getParameter("TEMP_HIGH").equals("")){
				 //TEMP_HIGH=Integer.parseInt("");
			}
			else{
				TEMP_HIGH=Integer.parseInt(request.getParameter("TEMP_HIGH"));
			}
			
			
			
			String sql="insert into SPCL_PATIENT_RECO VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int res=Dao.insertIntoSpclPatientReco(patientSSn, weightFreq, Weight_Low, Weight_high, BP_FREQ, BP_SYS_LOW, BP_SYS_HIGH, BP_DYS_LOW, 
					BP_DYS_HIGH, OS_FREQ, OS_LOW, OS_HIGH, PAIN_FREQ, PAIN_HIGH, MOOD_FREQ, MOOD_VAL, TEMP_FREQ, TEMP_LOW, TEMP_HIGH, sql);
			if(res>0){
				request.setAttribute("msg", "entered successfully");
				getServletContext().getRequestDispatcher("/editPatientReco.jsp").include(request, response);
			}
			else{
				request.setAttribute("msg", "some problem");
				getServletContext().getRequestDispatcher("/editPatientReco.jsp").include(request, response);
			}
			
		}
		
		
		
	}

}
