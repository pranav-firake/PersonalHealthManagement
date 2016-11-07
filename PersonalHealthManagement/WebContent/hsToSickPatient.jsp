<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action='HsToSickPatient' method='post'>
<table align='center'>



<tr><td>Disease</td>
<td><input type='text' min="0" step="1" name='disease'/></td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td>Diagnosed Date</td>
<td><input type='text' name='diseaseDate'/></td>
</tr>

<tr><td></td></tr><tr><td></td></tr>


<tr>
<td>HS SSN</td>
<td><input type='text' name='hsSSNval'/></td>
</tr>

<tr>
<td>HS DOA</td>
<td><input type='text' name='hsDOA'/></td>
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