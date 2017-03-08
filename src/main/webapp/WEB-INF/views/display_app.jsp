<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application List</title>
<script type='text/javascript'
	src='http://code.jquery.com/jquery-1.10.1.js'></script>
<link rel="stylesheet" type="text/css"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<script type='text/javascript'
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">

<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</head>
<body style='margin: 30px'>
	<div class="container">
		<div class="row">


			<div class="col-md-12">
				<h1>Applicaton List</h1>
				<div class="table-responsive">
					<table id="mytable" class="table table-bordred table-striped"
						style="border: black">
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Type</th>
							<th>url</th>
							<th>emailId</th>
							<th>ip_address</th>
							<th>new Status</th>
							<th>old Status</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<tr>
							<td><c:out value="${ill.applicationId}" /></td>
							<td><c:out value="${ill.applicationName}" /></td>
							<td><c:out value="${ill.applicationType}" /></td>
							<td><c:out value="${ill.applicationURL}" /></td>
							<td><c:out value="${ill.emailId}" /></td>
							<td><c:out value="${ill.internalIpAddress}" /></td>
							<td><c:out value="${ill.newStatusCode}" /></td>
							<td><c:out value="${ill.oldStatusCode}" /></td>
						</tr>
							<c:forEach items="${applicationList}" var="application">
							<tr>
								<td><c:out value="${application.applicationId}" /></td>
								<td><c:out value="${application.applicationName}" /></td>
								<td><c:out value="${application.applicationType}" /></td>
								<td><c:out value="${application.applicationURL}" /></td>
								<td><c:out value="${application.emailId}" /></td>
								<td><c:out value="${application.internalIpAddress}" /></td>
								<td><c:out value="${application.newStatusCode}" /></td>
								<td><c:out value="${application.oldStatusCode}" /></td>

								<td><a class="btn btn-primary btn-xs"
									href="editApp?appId=<c:out value="${application.applicationId}"/>"><span
										class="glyphicon glyphicon-pencil"></span></a></td>
								<td><a class="btn btn-danger btn-xs"
									href="deleteApp?appId=<c:out value="${application.applicationId}" />"><span
										class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>