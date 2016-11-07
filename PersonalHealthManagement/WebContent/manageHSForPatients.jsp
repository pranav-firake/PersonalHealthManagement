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

<c:set var="ptype" value="primary"/>
<c:set var="stype" value="secondary"/>
<sql:query var="primaryResult"  dataSource="${con}">
    SELECT H_SSN FROM HEALTH_SUPPORTER where P_SSN=${patientSSN} AND TYPE=?
    <sql:param value="${ptype}"/>
</sql:query>

<sql:query var="secondaryResult"  dataSource="${con}">
    SELECT H_SSN FROM HEALTH_SUPPORTER where P_SSN=${patientSSN} AND TYPE=?
    <sql:param value="${stype}"/>
</sql:query>

<div align="right">
<a href="patientmain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>

<form action='ManageHealthSupportersPatientServlet' method='post'>
<table align='center'>



<tr><td>Primary HS</td>
<c:forEach var="users" items="${primaryResult.rows}">
<td><input type='text' min="0" step="1" name='primaryHSOriginal' readonly="readonly" value="${users.H_SSN}"/></td>
</c:forEach>
<td><input type='number' min="0" step="1" name='primaryHSNew'/></td>
<td><input type="submit" name="editADDPrimaryHS" value="Edit/Add"></td>
<td><input type="text" name="doaPrimary" placeholder="DD-MON-YY"></td>
<c:forEach var="users" items="${primaryResult.rows}">
<td><input type="submit" name="deletePrimaryHS" value="Delete"></td>
</c:forEach>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<c:forEach var="users" items="${primaryResult.rows}">
<td>Secondary HS</td>
<c:forEach var="secondUsers" items="${secondaryResult.rows}">
<td><input type='text' min="0" step="1" name='secondaryHSOriginal' id="sec" readonly="readonly" value="${secondUsers.H_SSN}" /></td>
</c:forEach>
<td><input type='number' min="0" step="1" name='secondaryHSNew'/></td>
<td><input type="submit" name="editAddSecondaryHS" value="Edit/Add"></td>
<td><input type="text" name="doaSecondary" placeholder="DD-MON-YY"></td>
<c:forEach var="secondUsers" items="${secondaryResult.rows}">
<td><input type="submit" name="deleteSecondaryHS" value="Delete"></td>
</c:forEach>
</tr>
</c:forEach>



<tr><td></td></tr><tr><td></td></tr>

</table>

 ${msg}

</form>
</body>
</html>