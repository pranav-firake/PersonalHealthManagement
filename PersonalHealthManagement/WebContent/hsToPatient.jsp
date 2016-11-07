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
<body>

<BODY onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">

<div align="right">
<a href="healthSuppMain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>

<form action='HSToPatientPrev' method='post'>
<table align='center'>





<tr><td></td></tr><tr><td></td></tr>
<tr>
<td>Select Type</td>
<td><input type="radio" name="type" value="sick"/>Sick
<input type="radio" name="type" value="well"/>Well
</td>
</tr>



<tr><td></td></tr><tr><td></td></tr>
<tr>
<td></td>
<td><input type='submit' name="submit" value='SUBMIT'/></td>
</tr>
</table>

${msg}

</form>


</body>
</html>