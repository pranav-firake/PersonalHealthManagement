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
<h1>View OBS</h1>

<div align="right">
<a href="patientmain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>
		<form action='CheckOBSServlet' method='post'>
<table align='center'>




<tr>
<td></td>
<td><input type='submit' name="BPsubmit" value='View BP OBS'/></td>
</tr>
<tr>
<td></td>
<td><input type='submit' name="Moodsubmit" value='View Mood OBS'/></td>
</tr>
<tr>
<td></td>
<td><input type='submit' name="OSsubmit" value='View OS OBS'/></td>
</tr>
<tr>
<td></td>
<td><input type='submit' name="Painsubmit" value='View Pain OBS'/></td>
</tr>
<tr>
<td></td>
<td><input type='submit' name="Weightsubmit" value='View Weight OBS'/></td>
</tr>
<tr>
<td></td>
<td><input type='submit' name="Tempsubmit" value='View Temp OBS'/></td>
</tr>
</table>

${msg}

</form>


</body>
</html>