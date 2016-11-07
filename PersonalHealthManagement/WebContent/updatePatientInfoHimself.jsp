<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</head>

<BODY onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">

<body>

<sql:setDataSource
	var="con"
	driver="oracle.jdbc.driver.OracleDriver"
    url="jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01"
    user="rpathur" password="200157766"
/>

<sql:query var="result"  dataSource="${con}">
    SELECT PASSWORD,NAME,ADDRESS,DOB,GENDER FROM PERSONS where SSN=${patientSSN}
</sql:query>

<sql:query var="result2"  dataSource="${con}">
    SELECT * FROM SICKPERSON where SSN=${patientSSN}
</sql:query>

<sql:query var="result3"  dataSource="${con}">
    SELECT * FROM WELLPERSON where SSN=${patientSSN}
</sql:query>

<sql:query var="result4"  dataSource="${con}">
    SELECT * FROM HEALTH_SUPPORTER where P_SSN=${patientSSN}
</sql:query>

<%int count=0;%> 

<c:set var="count" value="0" scope="page" />
<c:set var="countHS" value="0" scope="page" />

<div align="right">
<a href="patientmain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>

<div align="center">
<form action='UpdatePatientInfoServlet' method='post'>
        <table border="1" cellpadding="5">
            <caption><h2>PATIENT INFO</h2></caption>
            
            
           
            <c:forEach var="user" items="${result.rows}">
           
			 
            <tr><td>USERNAME</td>
			<td><input type='text' name='name' value="${user.NAME}" /></td>
			</tr>
			<tr><td>PASSWORD</td>
			<td><input type='text' name='pass' value="${user.PASSWORD}" /></td>
			</tr>
			<tr><td>ADDRESS</td>
			<td><input type='text' name='address' value="${user.ADDRESS}" /></td>
			</tr>
			<tr><td>DOB</td>
			<td><input type='text' name='dob'  value="${user.DOB}" /></td>
			</tr>
			<tr><td>GENDER</td>
			<td><input type='text' name='gender' value="${user.GENDER}" /></td>
			</tr>
			 </c:forEach>
			<tr>
			<td><input type='submit' name='UpdateGeneralInfo' value="UpdateGeneralInfo" /></td>
			</tr>
			</table>
			
			<table  border="1" cellpadding="5">
           
            <caption><h2>DISEASE SECTION</h2></caption> 
            <c:forEach var="user2" items="${result2.rows}">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr><td>DISEASE:<c:out value="${count}" /></td>
			<td><input type='text' name='diseaseSick' value="${user2.DISEASE}" /></td>
			<td><input type='text' name='diseaseSickDiagnosed' value="${user2.DIAGNOSED}" /></td>
			<td><input type='hidden' name='diseaseSickPrev' value="${user2.DISEASE}" /></td>
			<td><input type='submit' name="updateDisease" value='Update'/></td>
			<td><input type='submit' name="deleteDisease" value='Delete'/></td>
			<td></td>
			</tr>
			   
            </c:forEach>
            
            <c:if test="${count !=3 && count!=0}">
   				<tr>
   				<td>Enter Disease</td>
   				<td><input type='text' name='diseaseNewSick'  placeholder="Enter disease name"/></td>
   				
				<td><input type='text' name='diseaseSickDiagnosedNew' placeholder="DD-MON-YY" /></td>
				
				<td><input type='submit' name="addDiseaseForSick" value='Add'/></td>
				</tr>
			</c:if>
			
			<c:forEach var="user4" items="${result4.rows}">
			<c:set var="countHS" value="${countHS + 1}" scope="page"/>
			<input type="hidden" name="noHSWell" value="${user4.P_SSN}"/>
			</c:forEach>
			
			<c:forEach var="user3" items="${result3.rows}">
			<tr>
   				<td>Enter Disease</td>
   				<td><input type='text' name='diseaseNewWell'  placeholder="Enter disease name"/></td>
   				
				<td><input type='text' name='diseaseWellDiagnosedNew' placeholder="DD-MON-YY" /></td>
				
				<td><input type='submit' name="addDiseaseForWell" value='ADD'/></td>
				
				<c:if test="${countHS==0}">
				
				<td><input type='text' name='wellNoHS'  placeholder="Enter HS SSN"/></td>
				<td><input type='text' name='wellNoHSDOA'  placeholder="Enter DOA"/></td>
				<td><input type="hidden" name="noHSWell" value="check"/></td>
				</c:if>
				
				</tr>
			</c:forEach>
            
        </table>
        </form>
     ${msg}   
    </div>
    
    
    

</body>
</html>