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
    SELECT NAME,ADDRESS,DOB,GENDER FROM PERSONS where SSN=${patientSSNAlert}
</sql:query>

<sql:query var="result2"  dataSource="${con}">
    SELECT * FROM SICKPERSON where SSN=${patientSSNAlert}
</sql:query>
<%int count=0;%> 

<c:set var="count" value="0" scope="page" />

<div align="right">
<a href="healthSuppMain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>

<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>PATIENT INFO</h2></caption>
           
            <c:forEach var="user" items="${result.rows}">
            <tr><td>USERNAME</td>
			<td><c:out value="${user.NAME}" /></td>
			</tr>
			 <tr><td>ADDRESS</td>
			<td><c:out value="${user.ADDRESS}" /></td>
			</tr>
			 <tr><td>DOB</td>
			<td><c:out value="${user.DOB}" /></td>
			</tr>
			 <tr><td>GENDER</td>
			<td><c:out value="${user.GENDER}" /></td>
			</tr>   
            </c:forEach>
            
            <c:forEach var="user2" items="${result2.rows}">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr><td>DISEASE:<c:out value="${count}" /></td>
			<td><c:out value="${user2.DISEASE}" /></td>
			</tr>
			   
            </c:forEach>
            
        </table>
    </div>
    

</body>
</html>