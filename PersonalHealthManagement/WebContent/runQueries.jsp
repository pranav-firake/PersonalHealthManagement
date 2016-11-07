<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</head>


   <BODY onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
<body>
<div align="right">
<a href="login.jsp" >BACK</a>
</div>
<form action='QueryServlet' method='post'>
		<input type="submit" value="Query 1 :" name="qa"><br><hr>
		<br>
		
		<input type="submit" value="Query 2 :" name="qb"><br><hr>
		<br>
		
		<input type="submit" value="Query 3 :" name="qc"><br><hr>
		<br>
		
		<input type="submit" value="Query 4 :" name="qd"><br><hr>
		<br>
		
		<input	type="submit" value="Query 5 :" name="qe">
	</form></body>
</html>