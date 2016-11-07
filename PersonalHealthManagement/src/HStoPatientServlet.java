

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HStoPatientServlet
 */
public class HStoPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HStoPatientServlet() {
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
		
		if(request.getParameter("submit")!=null){
			
			if(!request.getParameter("disease").equals("")){
				String disease=request.getParameter("disease");
			}
			if(!request.getParameter("diseaseDate").equals("")){
				String diseaseDate=request.getParameter("diseaseDate");
			}
			if(request.getParameter("type")!=null){
				String type=request.getParameter("type");
			}
			if(!request.getParameter("hsSSNval").equals("")){
				String hsSSNval=request.getParameter("hsSSNval");
			}
			if(!request.getParameter("hsDOA").equals("")){
				String hsDOA=request.getParameter("hsDOA");
			}
			
			
			
		}
		
		
	}

}
