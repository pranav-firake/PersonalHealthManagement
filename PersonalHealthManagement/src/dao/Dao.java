package dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Persons;


public class Dao {

	
	public static Connection connect() {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01","rpathur","200157766");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static ResultSet checkPerson(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet getDiseaseList(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int updateHS(int hssn,int pssn, String doa, int oldHSSSN,  String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		int result=0;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 ps.setInt(1, hssn);
				 ps.setString(2, doa);
				 ps.setInt(3, pssn);
				 ps.setInt(4, oldHSSSN);
				
				 
				
				result=ps.executeUpdate();
				
				
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return result;
	}
	
	public static int addHS(int hssn,int pssn, String doa, String type, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		int result=0;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				 ps.setInt(1, hssn);
				 ps.setInt(2, pssn);
				 ps.setString(3, doa);
				 ps.setString(4, type);
				result=ps.executeUpdate();
				
				
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return result;
	}
	
	public static int deleteHS(int hssn,int pssn,  String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		int result=0;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				 ps.setInt(1, hssn);
				 ps.setInt(2, pssn);
				 
				result=ps.executeUpdate();
				
				
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return result;
	}
	
	
	
	
	public static int deleteAlert(int alertID,  String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		int result=0;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				 ps.setInt(1, alertID);
				 
				result=ps.executeUpdate();
				
				
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return result;
	}
	
	
	
	
	public static ResultSet loginPerson(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				ps.setString(2, p.getPassword());
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet loginSickPatient(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int getRecordsMaxOBSID( String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		int max=0;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				
				
				rs=ps.executeQuery();
				if(rs.next()){
					max=rs.getInt(1);
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return max;
	}
	
	
	public static ResultSet loginWellPatient(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet checkOS(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet checkBP(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet checkMood(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	public static ResultSet checkTemp(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet checkWeight(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet checkPain(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	public static ResultSet loginHealthSupporter(Persons p, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, p.getPerson_Id());
				
				
				rs=ps.executeQuery();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	
	public static ResultSet checkRecords(int obsID, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				ps.setInt(1, obsID);
				
				
				rs=ps.executeQuery();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int enterRecords(int ssN,int obsID, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 ps.setInt(1,ssN);
				ps.setInt(2,obsID);
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int insertBP(int obsID, int bpSys, int bpDys, String obsDate, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				ps.setInt(1, obsID);
				ps.setInt(2, bpSys);
				ps.setInt(3, bpDys);
				ps.setString(4, obsDate);
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	public static int insertTemp(int obsID, int temp, String obsDate, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 ps.setInt(1, obsID);
				 ps.setInt(3, temp);
				 ps.setString(2, obsDate);
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	public static int insertWeight(int obsID, int weight, String obsDate, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 ps.setInt(1, obsID);
				 ps.setInt(3, weight);
				 ps.setString(2, obsDate);
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	public static int insertMood(int obsID, String mood, String obsDate, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				 ps.setInt(1, obsID);
				 ps.setString(3, mood);
				 ps.setString(2, obsDate);
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int insertOS(int obsID, int os, String obsDate, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				 ps.setInt(1, obsID);
				 ps.setInt(3, os);
				 ps.setString(2, obsDate);
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	public static int insertPain(int obsID, int pain, String obsDate, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 ps.setInt(1, obsID);
				 ps.setInt(3, pain);
				 ps.setString(2, obsDate);
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	
	public static int updateDiseaseForSick(int ssN,String disease, String newDate,String diseasePrev, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 ps.setString(1, disease);
				 ps.setString(2, newDate);
				 ps.setInt(3, ssN);
				 ps.setString(4, disease);
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int deleteDiseaseForSick(int ssN,String disease, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 ps.setString(2, disease);
				
				 ps.setInt(1, ssN);
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static ResultSet checkForSickPatient(int ssN, String sql){
		ResultSet rs=null;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				
				 ps.setInt(1, ssN);
				
				
				
				rs=ps.executeQuery();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int insertIntoSickPatient(int ssN,String disease,String diagnosed, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				
				 ps.setInt(1, ssN);
				 ps.setString(2, disease);
				 ps.setString(3, diagnosed);
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int insertIntoWellPatient(int ssN, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				
				 ps.setInt(1, ssN);
				 
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int deleteWellPatient(int ssN, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				
				 ps.setInt(1, ssN);
				 
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	public static int updateGeneralInfo(int ssN,String name,String pass,String address,String gender,String dob, String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				 String upd="update PERSONS set PASSWORD=?,NAME=?,ADDRESS=?,DOB=?,GENDER=? where SSN=?";
				
				 ps.setString(1, pass);
				 ps.setString(2, name);
				 ps.setString(3, address);
				 ps.setString(4, dob);
				 ps.setString(5, gender);
				 ps.setInt(6, ssN);
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	
	public static int insertIntoSpclPatientReco(int ssN,int weightFreq,int weightLow,int weightHigh,int bpFreq,int bpSysLow,int bpSysHigh,int bpDysLow,int bpDysHigh,
			int osFreq,int osLow,int osHigh,int painFreq,int painHigh,int moodFreq,String moodval,int tempFreq,int tempLow,int temphigh,
			String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				
				 ps.setInt(1, ssN);
				 
				 if(weightFreq==0){
					 
					ps.setNull(2, java.sql.Types.INTEGER);
					 
				 }else{
					 ps.setInt(2, weightFreq);
				 }
				 
				 if(weightLow==0){
					 
						ps.setNull(3, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(3, weightLow);
					 }
				 
				 if(weightHigh==0){
					 
						ps.setNull(4, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(4, weightHigh);
					 }
				 
				 
				 if(bpFreq==0){
					 
						ps.setNull(5, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(5, bpFreq);
					 }
				 
				 
				 if(bpSysLow==0){
					 
						ps.setNull(6, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(6, bpSysLow);
				 }
				 
				 
				 if(bpSysHigh==0){
					 
						ps.setNull(7, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(7, bpSysHigh);
				 }
				 
				 if(bpDysLow==0){
					 
						ps.setNull(8, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(8, bpDysLow);
					 }
				
				 
				 if(bpDysHigh==0){
					 
						ps.setNull(9, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(9, bpDysHigh);
				 }
				 
				 
				 
				 if(osFreq==0){
					 
						ps.setNull(10, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(10, osFreq);
				 }
				 
				 
				 
				 if(osLow==0){
					 
						ps.setNull(11, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(11, osLow);
				 }
				 
				 
				 if(osHigh==0){
					 
						ps.setNull(12, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(12, osHigh);
				 }
				 
				 
				 if(painFreq==0){
					 
						ps.setNull(13, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(13, painFreq);
				 }
				 
				 
				 
				 if(painHigh==0){
					 
						ps.setNull(14, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(14, painHigh);
				 }
				 
				 
				 if(moodFreq==0){
					 
						ps.setNull(15, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(15, moodFreq);
				 }
				 
				 
				 if(tempFreq==0){
					 
						ps.setNull(17, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(17, tempFreq);
					 }
				 
				 
				 if(tempLow==0){
					 
						ps.setNull(18, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(18, tempLow); 
				 }
				 
				 
				 if(temphigh==0){
					 
						ps.setNull(19, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(19, temphigh);
				 }
				 
				 
				 
				
				 
				
//				 if(moodval.equals("")){
//					 
//						ps.setNull(16, java.sql.Types.VARCHAR);
//						 
//					 }
//				 else{
//					 ps.setInt(16, temphigh);
//				 }
				 
				 
				
				
				 
				
				 
				 
				 ps.setString(16, moodval);
				
				 
				 
				 
				 
				 
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
	
	public static int editIntoSpclPatientReco(int ssN,int weightFreq,int weightLow,int weightHigh,int bpFreq,int bpSysLow,int bpSysHigh,int bpDysLow,int bpDysHigh,
			int osFreq,int osLow,int osHigh,int painFreq,int painHigh,int moodFreq,String moodval,int tempFreq,int tempLow,int temphigh,
			String sql){
		int rs=0;
		Connection con=connect();
		PreparedStatement ps=null;
		try {
			if(con!=null){
				 ps=con.prepareStatement(sql);
				
				
				 ps.setInt(19, ssN);
				 
				 if(weightFreq==0){
					 
					ps.setNull(1, java.sql.Types.INTEGER);
					 
				 }else{
					 ps.setInt(1, weightFreq);
				 }
				 
				 if(weightLow==0){
					 
						ps.setNull(2, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(2, weightLow);
					 }
				 
				 if(weightHigh==0){
					 
						ps.setNull(3, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(3, weightHigh);
					 }
				 
				 
				 if(bpFreq==0){
					 
						ps.setNull(4, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(4, bpFreq);
					 }
				 
				 
				 if(bpSysLow==0){
					 
						ps.setNull(5, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(5, bpSysLow);
				 }
				 
				 
				 if(bpSysHigh==0){
					 
						ps.setNull(6, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(6, bpSysHigh);
				 }
				 
				 if(bpDysLow==0){
					 
						ps.setNull(7, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(7, bpDysLow);
					 }
				
				 
				 if(bpDysHigh==0){
					 
						ps.setNull(8, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(8, bpDysHigh);
				 }
				 
				 
				 
				 if(osFreq==0){
					 
						ps.setNull(9, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(9, osFreq);
				 }
				 
				 
				 
				 if(osLow==0){
					 
						ps.setNull(10, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(10, osLow);
				 }
				 
				 
				 if(osHigh==0){
					 
						ps.setNull(11, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(11, osHigh);
				 }
				 
				 
				 if(painFreq==0){
					 
						ps.setNull(12, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(12, painFreq);
				 }
				 
				 
				 
				 if(painHigh==0){
					 
						ps.setNull(13, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(13, painHigh);
				 }
				 
				 
				 if(moodFreq==0){
					 
						ps.setNull(14, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(14, moodFreq);
				 }
				 
				 
				 if(tempFreq==0){
					 
						ps.setNull(16, java.sql.Types.INTEGER);
						 
					 }else{
						 ps.setInt(16, tempFreq);
					 }
				 
				 
				 if(tempLow==0){
					 
						ps.setNull(17, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(17, tempLow); 
				 }
				 
				 
				 if(temphigh==0){
					 
						ps.setNull(18, java.sql.Types.INTEGER);
						 
					 }
				 else{
					 ps.setInt(18, temphigh);
				 }
				 
				 
				 
				
				 
				
//				 if(moodval.equals("")){
//					 
//						ps.setNull(16, java.sql.Types.VARCHAR);
//						 
//					 }
//				 else{
//					 ps.setInt(16, temphigh);
//				 }
				 
				 
				
				
				 
				
				 
				 
				 ps.setString(15, moodval);
				
				 
				 
				 
				 
				 
				
				
				
				rs=ps.executeUpdate();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//close(rs);
            //close(ps);
            //close(con);
		}
		return rs;
	}
	
    public static String convertToSQLDate(String temp){
    	if(temp.matches("^\\d{1,2}-[a-zA-Z]{3}-\\d{2}$")==true){
    		return temp;
    	}
    	else if(temp.matches("^\\d{1,2}-[a-zA-Z]{3}-\\d{4}$")==true){

    		return temp.substring(0, temp.length()-4)+temp.substring(temp.length()-2, temp.length());
    	}
    	else{
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    		try {
    			java.util.Date date = format.parse(temp);
    			return (new SimpleDateFormat("dd-MMM-yy").format(date).toString());
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    		return null;
    	}
    }
	
	
	
	  public static void close(Connection conn) {
	        if(conn != null) {
	            try { conn.close(); } 
	            catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }

	   static void close(PreparedStatement st) {
	        if(st != null) {
	            try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	   }

	    static void close(ResultSet rs) {
	        if(rs != null) {
	            try { rs.close(); } 
	            catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
}
