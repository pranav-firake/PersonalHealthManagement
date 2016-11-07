

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class UpdateHSINfoServlet
 */
public class UpdateHSINfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHSINfoServlet() {
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
		
		//update general info
				if(request.getParameter("UpdateGeneralInfo")!=null){
					if(request.getParameter("name").equals("") 
							|| request.getParameter("pass").equals("") 
							|| request.getParameter("address").equals("") 
							|| request.getParameter("dob").equals("") 
							|| request.getParameter("gender").equals("")){
						
						request.setAttribute("msg", "some info empty");
						getServletContext().getRequestDispatcher("/updateHSInfo.jsp").include(request, response);
						
					}else{
						String name=request.getParameter("name");
						String pass=request.getParameter("pass");
						String address=request.getParameter("address");
						String dob=request.getParameter("dob");
						
						
						String dobfinal=Dao.convertToSQLDate(dob);
						//System.out.println(dob);
						//String dobFinal =new String();
						//DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.MS", Locale.ENGLISH);
//						 try {
//							java.util.Date dobDate =  format.parse(dob);
//							SimpleDateFormat f=new SimpleDateFormat("dd-MMM-YY");
//							dobFinal = f.format(dobDate);
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						
						String gender=request.getParameter("gender");
						
						String upd="update PERSONS set PASSWORD=?,NAME=?,ADDRESS=?,DOB=?,GENDER=? where SSN=?";
						
						int upres=Dao.updateGeneralInfo(hsSSN, name, pass, address, gender,dobfinal, upd);
						if(upres>0){
							request.setAttribute("msg", "Succesfully updated general info");
							getServletContext().getRequestDispatcher("/updateHSInfo.jsp").forward(request, response);
						}
						else{
							request.setAttribute("msg", "some problem");
							getServletContext().getRequestDispatcher("/updateHSInfo.jsp").forward(request, response);
						}
						
					}
				}
				
				if(request.getParameter("becomePatientPat")!=null){
					
					getServletContext().getRequestDispatcher("/hsToPatient.jsp").forward(request, response);
				}
				
	}

}
