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
    SELECT * FROM ALERT where SSN=${patientSSNAlert}
</sql:query>

<div align="right">
<a href="healthSuppMain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>

<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of alerts</h2></caption>
            <tr>
                
                <th>Alert_MSG</th>
                <th>Type</th>
                <th>Action</th>
            </tr>
            <c:forEach var="user" items="${result.rows}">
                <tr>
                    
                    <td><c:out value="${user.ALERTMSG}" /></td>
                    <td><c:out value="${user.TYPEOFALERT}" /></td>
                    <td>
                    <form action="ClearAlertsPatientSpecificByHSServlet" method="post">
                    <input type="hidden" name="alertID" value="${user.ALERTID}">
                    <input type="submit" name="delete" value="Delete Alert">
                    </form>
                    </td>
                    
                </tr>
            </c:forEach>
        </table>
    </div>
   
  ${msg} 

</body>
</html>