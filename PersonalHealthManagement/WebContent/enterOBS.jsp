<%@page import="java.sql.SQLException"%>
<%@page import="model.Persons"%>
<%@page import="dao.Dao"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.apache.catalina.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
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

<h1>Enter OBS</h1>

<sql:setDataSource
    var="con"
    driver="oracle.jdbc.driver.OracleDriver"
    url="jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01"
    user="rpathur" password="200157766"
/>


<sql:query var="result"  dataSource="${con}">
    SELECT * from SICKPERSON where SSN=${patientSSN}
</sql:query>


<div align="right">
<a href="patientmain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>


<form action='EnterObsByPatientsServlet' method='post'>
<table align='center'>




<tr><td></td></tr>
<tr><td></td></tr>


<tr>

<td>BP Sys</td>
<td><input type='text' name='bpSys'/></td>

</tr>
<tr>
<td>BP Dys</td>
<td><input type='text' name='bpDys'/></td>
</tr>
<tr>
<td>TEMP</td>
<td><input type='text' name='temp'/></td>
</tr>
<tr>
<td>WEIGHT</td>
<td><input type='text' name='weight'/></td>
</tr>
<tr>
<td>MOOD</td>
<td><input type='text' name='mood'/></td>
</tr>
<tr>
<td>OS</td>
<td><input type='text' name='os'/></td>
</tr>
<tr>
<td>Pain</td>
<td><input type='text' name='pain'/></td>
</tr>
<tr>
<td>OBS Date</td>
<td><input type='text' name='obsDate' placeholder="DD-MON-YY"/></td>
</tr>


<tr><td></td></tr><tr><td></td></tr>

<tr><td></td></tr><tr><td></td></tr>
<tr>
<td></td>
<td><input type='submit' name="submitOBS" value='SUBMIT'/></td>
</tr>
</table>

${msg}

</form>

</body>
</html>