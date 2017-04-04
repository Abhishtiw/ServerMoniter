<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>AHS-Status Report</title>
<link rel="stylesheet" href="resources/css/style.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
a img {
	height: 50px;
	width: 150px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<div id="leftWrapper">
			<div id="listView" class="list">
				<ul style="padding-top: -; padding-left: 0px;">
					<li class="list-item-active" style="height: 60px;"><a
						href="applicationstatus"
						style="padding-right: 2px; padding-top: 0px; padding-left: -; border-bottom-width: 3px; padding-bottom: 2px; height: 60px; padding-left: 2px; padding-left: 0px; border-top-width: 0px;"><img
							src="resources/image/estuate.jpg" alt="logo"
							style="padding-left: 0; border-right-width: 5px; padding-right: 5px; width: 215px; height: 60px;"></a></li>
					<li><a href="addApplication">Add Application</a></li>
					<li><a href="addUser">Add User</a></li>
						<li><a href="addIll">Add ILL</a></li>
					<li><a href="displayApplication">View Applications</a></li>
					<li><a href="displayUser">View Users</a></li>
					<li><a href="applicationstatus">Application Status</a></li>
					<li><a href="applicationhealthstatus">Health Status
							History</a></li>
					<li><a href="signout"
						style="border-bottom: solid 1px rgba(0, 0, 0, 0.2);">Sign Out</a></li>
				</ul>
			</div>
		</div>
		<div id="rightWrapper">
			<div id="header" style="border-bottom-width: 1px;">
				<a id="fullPage" href="#">|||</a>
				<%-- 	<label>${UserMessage}</label> --%>
			</div>
			<br> <br> <br> <br> <br>

			<h3 style="color: #003366">ILL Report</h3>
			<label style="color: red">${UserMessage}</label>
			<div class="table-responsive">
				<table id="tab" class="table table-hover">
					<thead style="white-space: nowrap;color: #003366;">
						<tr>
							<th>Provider</th>
							<th>Ip Address</th>
							<th>Location</th>
							<th>Impact</th>
							<th>Current Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${illList}" var="ill">
							<tr>
								<td><c:out value="${ill.providerName}" /></td>
								<td><c:out value="${ill.internalIpAddress}" /></td>
								<td><c:out value="${ill.location}" /></td>
								<td><c:out value="${ill.impact}" /></td>
								<c:choose>
									<c:when
										test="${(ill.currentStatus >= 200) && (ill.currentStatus <= 399)}">
										<td><span title="ISP Up"><img alt="Up"
												src="resources/image/up.png"></span></td>
									</c:when>
									<c:otherwise>
									<td><span title="ISP Down"><img alt="Down" src="resources/image/down.png"></span></td>
								</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
				</table>

<br><br>

				<h3 style="color: #003366">Application Report</h3>
				<label style="color: red">${UserMessage}</label>
				<div class="table-responsive">
					<table id="tab" class="table table-hover">
						<thead style="white-space: nowrap;color: #003366;">
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
							<%-- <c:if
						test="${(ill.newStatusCode >= 200) && (ill.newStatusCode <= 399)}"> --%>
							<c:forEach items="${applicationStatus}" var="application">
								<tr>
									<td><c:out value="${application.applicationName}" /></td>
									<td><c:out value="${application.applicationType}" /></td>
									<td><c:out value="${application.applicationURL}" /></td>
									<td><c:out value="${application.internalIpAddress}" /></td>
									<td><span title="<c:out value="${application.message}" />"><c:out
												value="${application.newStatusCode}" /></span></td>
									<td><c:out value="${application.responseGeneratedTime}" /></td>
									<c:choose>
										<c:when
											test="${(application.newStatusCode >= 200) && (application.newStatusCode <= 399)}">
											<td><span
												title="<c:out value="${application.message}" />"><img
													alt="Up" src="resources/image/up.png"></span></td>
										</c:when>
										<c:otherwise>
											<td><span
												title="<c:out value="${application.message}" />"><img
													alt="Down" src="resources/image/down.png"></span></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
							<%-- 	</c:if> --%>
					</table>
				</div>
			</div>
			<!-- <div align="right"> -->
			<form></form>
			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
			<%-- <script>
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
														a.href = data_type
																+ ', '
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
			</center> --%>
			<script
				src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
			<script src="resources/js/index.js"></script>
</body>
</html>

