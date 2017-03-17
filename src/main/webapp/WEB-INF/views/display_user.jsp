<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/css/style1.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<body>
 <div id="wrapper">
		<div id="leftWrapper">
			<div id="listView" class="list">
				<ul style="padding-top: -;padding-left: 0px;">
					<li class="list-item-active" style="height: 60px;"><a href="applicationstatus" style="padding-right: 2px;
    padding-top: 0px;
    padding-left: -;
    border-bottom-width: 3px;
    padding-bottom: 2px;
    height: 60px;
    padding-left: 2px;
    padding-left: 0px;border-top-width: 0px;"><img src="resources/image/estuate.jpg" alt="logo" style="
    padding-left: 0;
    border-right-width: 5px;
    padding-right: 5px;
    width: 215px;
    height: 60px;
    "></a>::after</li>
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
			<div id="header" style="border-bottom-width:1px; " >
				<a id="fullPage" href="#">|||</a>
				<label></label>
			</div>
			<br> <br> <br> <br> <br>
			<div class="row">
				<div class="col-md-12">
					<h1>User List</h1>
					<div class="table-responsive">
						<table id="mytable" class="table table-bordred table-striped">
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Email</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
							<c:forEach items="${user}" var="user">
								<tr>
									<td><c:out value="${user.id}" /></td>
									<td><c:out value="${user.userName}" /></td>
									<td><c:out value="${user.emailId}" /></td>
									<td><a class="btn btn-primary btn-xs"
										href="editUser?userId=<c:out value="${user.id}"/>"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
									<td><a class="btn btn-danger btn-xs"
										href="deleteUser?userId=<c:out value="${user.id}"/>&mailId=<c:out value="${user.emailId}" />" onclick="return confirm('Are you sure you want to delete!!?');"><span
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