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

<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>
<div id="menu">
		<ul>
		<li><a href="healthsupporter.jsp">View Patients</a></li>
			<li><a href="updateHSInfo.jsp" >Update Info</a></li>
			
			
		</ul>
	</div>
</body>
</html>