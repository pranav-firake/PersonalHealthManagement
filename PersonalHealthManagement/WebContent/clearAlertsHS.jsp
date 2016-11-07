<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="ClearAlertsHSServlet" method="post">
<table align='center'>



<tr><td>Enter Patient ID</td>
<td><input type='text' min="0" step="1" name='patient_id' required="true"/></td>
</tr>

<tr>
<td></td>
<td><input type='submit' name="submit" value='SUBMIT'/></td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>

</table>
</form>

</body>
</html>