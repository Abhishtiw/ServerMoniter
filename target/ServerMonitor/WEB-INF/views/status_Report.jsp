<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Bootstrap Example</title>
<link rel="stylesheet" href="resources/css/style1.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
	<div id="wrapper">
		<div id="leftWrapper">
			<div id="listView" class="list">
				<li class="list-item-active"><a href="applicationstatus">SERVER
						MONITOR</a></li>
				<li><a href="addApplication">Add Application</a></li>
				<li><a href="addUser">Add User</a></li>
				<li><a href="displayApplication">View Applications</a></li>
				<li><a href="displayUser">View Users</a></li>
				<!-- <li><a href="#">Application Status</a></li> -->
				<li><a href="applicationstatus">Status Report</a></li>
				<li><a href="#">Email History</a></li>
				<li><a href="signout">Sign Out</a></li>
			</div>
		</div>
		<div id="rightWrapper">
			<div id="header">
				<a id="fullPage" href="#">|||</a>
			</div>
			<br> <br> <br> <br> <br>
			<h2>Report</h2>
			<table class="table table-bordred table-striped">
				<thead>
					<tr>
						<th>Application Name</th>
						<th>Application Type</th>
						<th>URL</th>
						<th>IP Address</th>
						<th>Response Code</th>
						<th>Response generated time</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${ill.applicationName}" /></td>
						<td><c:out value="${ill.applicationType}" /></td>
						<td><c:out value="${ill.applicationURL}" /></td>
						<td><c:out value="${ill.internalIpAddress}" /></td>
						<td><c:out value="${ill.newStatusCode}" /></td>
						<td><c:out value="${ill.responseGeneratedTime}" /></td>
						<c:choose>
							<c:when
								test="${(ill.newStatusCode >= 200) && (ill.newStatusCode <= 399)}">
								<td><img alt="Up" src="resources/image/up.png"></td>
							</c:when>
							<c:otherwise>
								<td><img alt="Down" src="resources/image/down.png"></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<c:if
						test="${(ill.newStatusCode >= 200) && (ill.newStatusCode <= 399)}">
						<c:forEach items="${applicationStatus}" var="application">
							<tr>
								<td><c:out value="${application.applicationName}" /></td>
								<td><c:out value="${application.applicationType}" /></td>
								<td><c:out value="${application.applicationURL}" /></td>
								<td><c:out value="${application.internalIpAddress}" /></td>
								<td><c:out value="${application.newStatusCode}" /></td>
								<td><c:out value="${application.responseGeneratedTime}" /></td>
								<c:choose>
									<c:when
										test="${(application.newStatusCode >= 200) && (application.newStatusCode <= 399)}">
										<td><img alt="Up" src="resources/image/up.png"></td>
									</c:when>
									<c:otherwise>
										<td><img alt="Down" src="resources/image/down.png"></td>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:forEach>
					</c:if>
			</table>


		</div>
		<!-- <div align="right"> -->

		<form></form>

		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script>
			$(document)
					.ready(
							function() {
								$("#btnExport")
										.click(
												function(e) {
													e.preventDefault();

													//getting data from our table
													var data_type = 'data:application/vnd.ms-excel';
													var table_div = document
															.getElementById('table_wrapper');
													var table_html = table_div.outerHTML
															.replace(/ /g,
																	'%20');

													var a = document
															.createElement('a');
													a.href = data_type + ', '
															+ table_html;
													a.download = 'exported_table_'
															+ Math
																	.floor((Math
																			.random() * 9999999) + 1000000)
															+ '.xls';
													a.click();
												});
							});
		</script>
		<center>
			<button id="btnExport">Export to xls</button>
		</center>
		<script
			src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

		<script src="resources/js/index.js"></script>
</body>
</html>

