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

<sql:setDataSource
    var="con"
    driver="oracle.jdbc.driver.OracleDriver"
    url="jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01"
    user="rpathur" password="200157766"
/>

<sql:query var="result"  dataSource="${con}">
    SELECT * FROM SPCL_PATIENT_RECO where SSN=${patientSSNAlert}
</sql:query>

<form action='EditPatientRecoServlet' method='post'>


<c:set var="count" value="0" scope="page" />
<h1>Enter Reco</h1>



<c:forEach var="user" items="${result.rows}">
<c:set var="count" value="${count + 1}" scope="page"/>

<div align="right">
<a href="healthSuppMain.jsp" >BACK</a>
<a href="<%=request.getContextPath()%>/LogoutServlet">LOGOUT</a>
</div>

<table align='center'>
<tr><td>Weight_Freq</td>
<td><input type='text' min="0" step="1" name='Weight_Freq' value="${user.WEIGHT_FREQ}"/></td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td>Weight_Low</td>
<td><input type='text' name='Weight_Low' value="${user.WEIGHT_LOW}"/></td>
</tr>

<tr><td></td></tr><tr><td></td></tr>


<tr>
<td>Weight_high</td>
<td><input type='text' name='Weight_high'  value="${user.WEIGHT_HIGH}"/></td>
</tr>

<tr>
<td>BP_FREQ</td>
<td><input type='text' name='BP_FREQ' value="${user.BP_FREQ}"/></td>
</tr>

<tr>
<td>BP_SYS_LOW</td>
<td><input type='text' name='BP_SYS_LOW' value="${user.BP_SYS_LOW}"/></td>
</tr>

<tr>
<td>BP_SYS_HIGH</td>
<td><input type='text' name='BP_SYS_HIGH'  value="${user.BP_SYS_HIGH}"/></td>
</tr>

<tr>
<td>BP_DYS_LOW</td>
<td><input type='text' name='BP_DYS_LOW' value="${user.BP_DYS_LOW}"/></td>
</tr>

<tr>
<td>BP_DYS_HIGH</td>
<td><input type='text' name='BP_DYS_HIGH' value="${user.BP_DYS_HIGH}"/></td>
</tr>

<tr>
<td>OS_FREQ</td>
<td><input type='text' name='OS_FREQ' value="${user.OS_FREQ}"/></td>
</tr>

<tr>
<td>OS_LOW</td>
<td><input type='text' name='OS_LOW' value="${user.OS_LOW}"/></td>
</tr>

<tr>
<td>OS_HIGH</td>
<td><input type='text' name='OS_HIGH' value="${user.OS_HIGH}"/></td>
</tr>

<tr>
<td>PAIN_FREQ</td>
<td><input type='text' name='PAIN_FREQ' value="${user.PAIN_FREQ}"/></td>
</tr>

<tr>
<td>PAIN_HIGH</td>
<td><input type='text' name='PAIN_HIGH' value="${user.PAIN_HIGH}"/></td>
</tr>

<tr>
<td>MOOD_FREQ</td>
<td><input type='text' name='MOOD_FREQ' value="${user.MOOD_FREQ}"/></td>
</tr>

<tr>
<td>MOOD_VAL</td>
<td><input type='text' name='MOOD_VAL' value="${user.MOOD_VAL}"/></td>
</tr>

<tr>
<td>TEMP_FREQ</td>
<td><input type='text' name='TEMP_FREQ' value="${user.TEMP_FREQ}"/></td>
</tr>

<tr>
<td>TEMP_LOW</td>
<td><input type='text' name='TEMP_LOW' value="${user.TEMP_LOW}"/></td>
</tr>

<tr>
<td>TEMP_HIGH</td>
<td><input type='text' name='TEMP_HIGH' value="${user.TEMP_HIGH}"/></td>
</tr>





<tr><td></td></tr><tr><td></td></tr>
<tr>
<td></td>
<td><input type='submit' name="edit" value='Edit'/></td>
</tr>
</table>


</c:forEach>




<c:if test="${count==0}">

<table align='center'>
<tr><td>Weight_Freq</td>
<td><input type='text' min="0" step="1" name='Weight_Freq'/></td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td>Weight_Low</td>
<td><input type='text' name='Weight_Low'/></td>
</tr>

<tr><td></td></tr><tr><td></td></tr>


<tr>
<td>Weight_high</td>
<td><input type='text' name='Weight_high'/></td>
</tr>

<tr>
<td>BP_FREQ</td>
<td><input type='text' name='BP_FREQ'/></td>
</tr>

<tr>
<td>BP_SYS_LOW</td>
<td><input type='text' name='BP_SYS_LOW'/></td>
</tr>

<tr>
<td>BP_SYS_HIGH</td>
<td><input type='text' name='BP_SYS_HIGH'/></td>
</tr>

<tr>
<td>BP_DYS_LOW</td>
<td><input type='text' name='BP_DYS_LOW'/></td>
</tr>

<tr>
<td>BP_DYS_HIGH</td>
<td><input type='text' name='BP_DYS_HIGH'/></td>
</tr>

<tr>
<td>OS_FREQ</td>
<td><input type='text' name='OS_FREQ'/></td>
</tr>

<tr>
<td>OS_LOW</td>
<td><input type='text' name='OS_LOW'/></td>
</tr>

<tr>
<td>OS_HIGH</td>
<td><input type='text' name='OS_HIGH'/></td>
</tr>

<tr>
<td>PAIN_FREQ</td>
<td><input type='text' name='PAIN_FREQ'/></td>
</tr>

<tr>
<td>PAIN_HIGH</td>
<td><input type='text' name='PAIN_HIGH'/></td>
</tr>

<tr>
<td>MOOD_FREQ</td>
<td><input type='text' name='MOOD_FREQ'/></td>
</tr>

<tr>
<td>MOOD_VAL</td>
<td><input type='text' name='MOOD_VAL'/></td>
</tr>

<tr>
<td>TEMP_FREQ</td>
<td><input type='text' name='TEMP_FREQ'/></td>
</tr>

<tr>
<td>TEMP_LOW</td>
<td><input type='text' name='TEMP_LOW'/></td>
</tr>

<tr>
<td>TEMP_HIGH</td>
<td><input type='text' name='TEMP_HIGH'/></td>
</tr>





<tr><td></td></tr><tr><td></td></tr>
<tr>
<td></td>
<td><input type='submit' name="submit" value='SUBMIT'/></td>
</tr>
</table>

</c:if>

${msg}

</form>

</body>
</html>