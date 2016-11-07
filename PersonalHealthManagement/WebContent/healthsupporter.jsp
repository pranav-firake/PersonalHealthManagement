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
<h1>Hello HS</h1>

<sql:setDataSource
    var="con"
    driver="oracle.jdbc.driver.OracleDriver"
    url="jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01"
    user="rpathur" password="200157766"
/>

<sql:query var="result"  dataSource="${con}">
    SELECT * FROM HEALTH_SUPPORTER where H_SSN=${hssn}
</sql:query>

<div align="right">
<a href="healthSuppMain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>

<h1>Hello ${hssn}</h1>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of patients</h2></caption>
            <tr>
                <th>HSSN</th>
                <th>PSSN</th>
                
                <th>DOA</th>
                <th>Type</th>
                <th>Action</th>
            </tr>
            <c:forEach var="user" items="${result.rows}">
                <tr>
                    <td><c:out value="${user.H_SSN}" /></td>
                    <td><c:out value="${user.P_SSN}" /></td>
                    
                    <td><c:out value="${user.DOA}" /></td>
                    <td><c:out value="${user.TYPE}" /></td>
                    <td>
                    <form action="ClearAlertsHSServlet" method="post">
                    <input type="hidden" name="pssn" value="${user.P_SSN}">
                    <input type="submit" name="submit" value="Delete Alerts">
                    <input type="submit" name="viewPatInfo" value="View Patient Info">
                    <input type="submit" name="editPatReco" value="Edit Patient Reco">
                    </form>
                    </td>
                    
                </tr>
            </c:forEach>
        </table>
    </div>
    
  

</body>
</html>