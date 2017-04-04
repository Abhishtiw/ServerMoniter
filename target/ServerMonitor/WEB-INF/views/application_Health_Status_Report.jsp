<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>AHS-Application Health Status Report</title>
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
				<li><a href="signout" style="border-bottom: solid 1px rgba(0, 0, 0, 0.2);">Sign Out</a></li>
				</ul>
			</div>
		</div>
		<div id="rightWrapper">
			<div id="header" style="border-bottom-width: 1px;">
				<a id="fullPage" href="#">|||</a> <label></label>
			</div>
			<br> <br> <br> <br> <br>
			<h2>Application Health Status Report</h2>
			<label style="color: red">${UserMessage}</label>
			<table id="tab" class="table table-hover">
				<thead style="white-space: nowrap;">
					<tr>
						<th>Application Id</th>
						<th>Application Name</th>
						<th>Current Status</th>
						<th>Email</th>
						<th>Cc(Mail)</th>
						<th>To(Mail)</th>
						<th>Response Generated Time</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${applicationHealthStatusReport}"
						var="application">
						<tr>
							<td><c:out value="${application.applicationId}" /></td>
							<td><c:out value="${application.applicationName}" /></td>
							<td><c:out value="${application.currentStatus}" /></td>
							<td><c:out value="${application.emailId}" /></td>
							<td><c:out value="${application.emailCc}" /></td>
							<td><c:out value="${application.emailTo}" /></td>
							<td><c:out value="${application.generatedTime}" /></td>
							<td><span title="<c:out value="${application.message}" />"><span
									class="crop"><c:out value="${application.message}" /></span></span></td>
						</tr>
					</c:forEach>
			</table>
		</div>
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