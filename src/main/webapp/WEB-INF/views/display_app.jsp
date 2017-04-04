<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AHS-Application List</title>
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
<link rel="stylesheet" type="text/css" media="screen"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="leftWrapper">
			<div id="listView" class="list">
				<ul style="padding-top: -; padding-left: 0px;color: #003366;">
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
				<a id="fullPage" href="#">|||</a> <label></label>
			</div>
			<br> <br> <br> <br> <br>
			<div class="row">
				<div class="col-md-12">
					<h3 style="color: #003366;">ILL List</h3>
					<label style="color: red">${IllMessage}</label>
					<div class="table-responsive">
						<table id="tab" class="table table-hover">
							<thead style="white-space: nowrap; color: #003366;">
								<tr>
									<th>Provider</th>
									<th>Ip Address</th>
									<th>Location</th>
									<th>Impact</th>
									<th>Primary ILL</th>
									<th>Current Status</th>
									<th>Edit</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${illList}" var="ill">
									<tr>
										<td><c:out value="${ill.providerName}" /></td>
										<td><c:out value="${ill.internalIpAddress}" /></td>
										<td><c:out value="${ill.location}" /></td>
										<td><c:out value="${ill.impact}" /></td>
										<c:if test="${ill.primaryIll eq 'true'}">
											<td>Yes</td>
										</c:if>
										<c:if test="${ill.primaryIll eq 'false'}">
											<td>No</td>
										</c:if>
										<td><c:out value="${ill.currentStatus}" /></td>
										<td><a class="btn btn-primary btn-xs"
											href="editIll?illId=<c:out value="${ill.id}"/>"><span
												class="glyphicon glyphicon-pencil"></span></a></td>
										<td><a class="btn btn-danger btn-xs"
											href="deleteIll?illId=<c:out value="${ill.id}" />"
											onclick="return confirm('Are you sure you want to delete!!?');"><span
												class="glyphicon glyphicon-trash"></span></a></td>
									</tr>
								</c:forEach>
						</table>
						<br> <br>
						<h3 style="color: #003366;">Applicaton List</h3>
						<label style="color: red">${UserMessage}</label>
						<div class="table-responsive">
							<table id="tab" class="table table-hover">
								<thead style="white-space: nowrap; color: #003366;">
									<tr>
										<th>Name</th>
										<th>Type</th>
										<th>url</th>
										<th>ip_address</th>
										<th>Current Status</th>
										<th>Previous Status</th>
										<th>active</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<c:forEach items="${applicationList}" var="application">
									<tr>
										<td><c:out value="${application.applicationName}" /></td>
										<td><c:out value="${application.applicationType}" /></td>
										<td><c:out value="${application.applicationURL}" /></td>
										<td><c:out value="${application.internalIpAddress}" /></td>
										<td><span
											title="<c:out value="${application.message}" />"><c:out
													value="${application.newStatusCode}" /></span></td>
										<td><c:out value="${application.oldStatusCode}" /></td>
										<c:if test="${application.active eq 'true'}">
											<td>Yes</td>
										</c:if>
										<c:if test="${application.active eq 'false'}">
											<td>No</td>
										</c:if>
										<td><a class="btn btn-primary btn-xs"
											href="editApp?appId=<c:out value="${application.id}"/>"><span
												class="glyphicon glyphicon-pencil"></span></a></td>
										<td><a class="btn btn-danger btn-xs"
											href="deleteApp?appId=<c:out value="${application.id}" />"
											onclick="return confirm('Are you sure you want to delete!!?');"><span
												class="glyphicon glyphicon-trash"></span></a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script
			src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src="resources/js/index.js"></script>
</body>
</html>