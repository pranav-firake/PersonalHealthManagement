<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<div align="right">
<a href="runQueries.jsp" >BACK</a>
</div>
<sql:setDataSource
    var="con"
    driver="oracle.jdbc.driver.OracleDriver"
    url="jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01"
    user="rpathur" password="200157766"
/>
<sql:query var="result"  dataSource="${con}">
 SELECT ssn, name 
FROM Persons P
WHERE SSN IN (Select SSN from WELLPERSON)

</sql:query>


<div align="center">
		<div style="height: 47px; ">
		List the patients who are not ‘sick’.		</div>
		<br><div>
		Query <br><br>
		SELECT ssn, name 
		FROM Persons P
		WHERE SSN IN (Select SSN from WELLPERSON);
		
		</div>
		<table border="1" cellpadding="5">
            <caption><h2>Query 4</h2></caption>
            <tr>
                <th>SSN</th>
                    <th>name</th>
                               
            </tr>
            <c:forEach var="user" items="${result.rows}">
                <tr>
                    <td><c:out value="${user.ssn}" /></td>
                     <td><c:out value="${user.name}" /></td>
                 </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>