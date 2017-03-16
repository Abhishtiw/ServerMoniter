<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>ApplicationHealthStatusReport</title>
<link rel="stylesheet" href="resources/css/style1.css">
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
				<ul style="padding-top: -;padding-left: 2px;">
					<li class="list-item-active"><a href="applicationstatus" style="padding-right: 2px;
    padding-top: 2px;
    padding-left: -;
    border-bottom-width: 3px;
    padding-bottom: 2px;
    height: 71px;
    padding-left: 2px;
    padding-left: 1px;"><img
							src="resources/image/logo.PNG" alt="logo" style="padding-left: 0;
    border-right-width: 5px;
    padding-right: 5px;
    width: 215px; height: 66.15px"></a></li>
					<li><a href="addApplication">Add Application</a></li>
					<li><a href="addUser">Add User</a></li>
					<li><a href="displayApplication">View Applications</a></li>
					<li><a href="displayUser">View Users</a></li>
					<li><a href="applicationhealthstatus">Health Status Report</a></li>
					<li><a href="signout">Sign Out</a></li>
				</ul>
			</div>
		</div>



		<div id="rightWrapper">
			<div id="header" style="border-bottom-width: 9px">
				<a id="fullPage" href="#">|||</a>
			</div>
			<br> <br> <br> <br> <br>
			<h2>Application Health Status Report</h2>
			<table class="table table-bordred table-striped">
				<thead>
					<tr>
						<th>Application Name</th>
						<th>Application Type</th>
						<th>URL</th>
						<th>Current Status</th>
						<th>Description</th>
						<th>Cc(Mail)</th>
						<th>To(Mail)</th>
						<th>Response Generated Time</th>
					</tr>
				</thead>
				<tbody>
					<%-- <tr>
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
						test="${(ill.newStatusCode >= 200) && (ill.newStatusCode <= 399)}"> --%>
						<c:forEach items="${applicationHealthStatusReport}" var="application">
							<tr>
								<td><c:out value="${applicationHealthStatusReport.applicationName}" /></td>
								<td><c:out value="${applicationHealthStatusReport.applicationType}" /></td>
								<td><c:out value="${applicationHealthStatusReport.url}" /></td>
								<td><c:out value="${applicationHealthStatusReport.newStatusCode}" /></td>
								<td><c:out value="${applicationHealthStatusReport.message}"/></td>
								<td><c:out value="${applicationHealthStatusReport.cc}"/></td>
								<td><c:out value="${applicationHealthStatusReport.to}"/></td>
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
					<%-- </c:if> --%>
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