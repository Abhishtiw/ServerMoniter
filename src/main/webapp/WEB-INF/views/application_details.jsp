<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Application Status</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Application ID</th>
					<th>Application Name</th>
					<th>IP Address</th>
					<th>URL</th>
					<th>Old Status</th>
					<th>New Status</th>
					<th>Response Generated Time</th>
					<th>Date</th>
				</tr>
			</thead>

			<c:forEach items="${applicationList}" var="application">
				<tr>
					<td><c:out value="${application.applicationId}" /></td>
					<td><c:out value="${application.applicationName}" /></td>
					<td><c:out value="${application.internalIpAddress}" /></td>
					<td><c:out value="${application.applicationURL}" /></td>
					<td><c:out value="${application.applicationType}" /></td>
					<td><c:out value="${application.oldStatusCode}" /></td>
					<td><c:out value="${application.newStatusCode}" /></td>
					<td><c:out value="${application.responseGeneratedTime}" /></td>
					<td><c:out value="${application.emailId}" /></td>
					
				</tr>
			</c:forEach>

		</table>
	</div>
	
</body>
</html>
