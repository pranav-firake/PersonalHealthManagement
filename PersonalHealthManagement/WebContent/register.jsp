<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<div id="welcome">
	<h3><b><center>REGISTER</center></b></h3>
	</div>
	
	
		<form action='RegisterServlet' method='post'>
<table align='center'>



<tr>
<td>Register As</td>
<td><input type="radio"  name="persons" value="p1"/>Patient
<input type="radio" name="persons" value="h1"/>Health Supporter


</tr>
<tr><td></td></tr><tr><td></td></tr>
<tr>
<td></td>
<td><input type='submit' name="continue" value='CONTINUE'/></td>
</tr>



</table>

${msg}

</form>

</body>
</html>