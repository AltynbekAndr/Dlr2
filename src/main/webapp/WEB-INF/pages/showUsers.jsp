<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Spring MVC List of objects display</title>
</head>
<body>
<form:form method="POST" action="viewPDF" modelAttribute="Users">
	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
		</tr>
		<c:forEach items="${Users.users}" var="user" varStatus="tagStatus">
			<tr>
				<td><form:input path="users[${tagStatus.index}].firstName" value="${user.firstName}" readonly="true"/></td>
				<td><form:input path="users[${tagStatus.index}].lastName" value="${user.lastName}" readonly="true"/></td>
				<td><form:input path="users[${tagStatus.index}].email" value="${user.email}" readonly="true"/></td>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="View PDF" />
</form:form>
</body>
</html>