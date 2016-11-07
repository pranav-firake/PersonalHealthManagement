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
<form action='LoginServlet' method='post'>
<div id="wrapper">
	<div id="header">
	
	
		<div id="logo">
			<h1>PERSONAL HEALTH MANAGEMENT </h1>
			<p>&nbsp;</p>
		</div>
	</div>
	<!-- end #header -->
<!-- 	<div id="menu"> -->
<!-- 		<ul> -->
<!-- 			<li><a href="index.jsp">HOME</a></li>		 -->
<!-- 		</ul> -->
<!-- 	</div> -->
	<div id="welcome">
	<h3><b><center>LOGIN</center></b></h3>
	</div>
		<form action='LoginServlet' method='post'>
<table align='center'>



<tr><td>USERNAME</td>
<td><input type='text' min="0" step="1" name='person_id'/></td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td>PASSWORD</td>
<td><input type='password' name='pass'/></td>
</tr>

<tr><td></td></tr><tr><td></td></tr>
<tr>
<td>Continue As</td>
<td><input type="radio" name="persons" value="p1"/>Patient
<input type="radio" name="persons" value="h1"/>Health Supporter


</tr>
<tr><td></td></tr><tr><td></td></tr>
<tr>
<td></td>
<td><input type='submit' name="submit" value='SUBMIT'/>
<input type="submit" name="register" value="Register">
<input  type="submit" name="run" value="Run Queries"></td>
</tr>
</table>

${msg}

</form>
</body>
</html>