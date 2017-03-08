<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style1.css">
<style>
.error {
	color: red;
	font-style: italic;
	font-size: 10px;
}

body {
	background-image: url("../image/45.jpg");
	font-family: Tahoma, Geneva, sans-serif;
}

#container {
	width: 550px;
	background-color: rgba(250, 250, 252, .9);
	margin: auto;
	margin-top: 10px;
	margin-bottom: 10px;
	box-shadow: 0 0 3px #999;
}

#container_body {
	padding: 20px;
}

.form_title {
	font-size: 35px;
	color: #141823;
	text-align: center;
	padding: 10px;
	font-weight: normal;
}

.head_para {
	font-size: 19px;
	color: #99a2a7;
	text-align: center;
	font-weight: normal;
}

.firstnameorlastname {
	margin-right: 20px;
}

.input_name {
	width: 207px;
	padding: 5px;
	font-size: 18px;
}

.input_num {
	width: 207px;
	padding: 5px;
	font-size: 18px;
}

.input_number {
	width: 434px;
	padding: 5px;
	font-size: 18px;
}

.input_email {
	width: 300px;
	padding: 5px;
	font-size: 18px;
}

.comments {
	width: 400px;
	padding: 5px;
	font-size: 18px;
	select
	{
	padding
	:
	5px;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/add_application.js"></script>
</head>

<body>

	<div id="wrapper">
		<div id="leftWrapper">
			<div id="listView" class="list">

				<li class="list-item-active"><a href="applicationstatus">SERVER MONITOR</a></li>
				<li><a href="addApplication">Add Application</a></li>
				<li><a href="addUser">Add User</a></li>
				<li><a href="displayApplication">View Applications</a></li>
				<li><a href="displayUser">View Users</a></li>
				<li><a href="applicationstatus">Status Report</a></li>
				<li><a href="#">Email History</a></li>
				<li><a href="signout">Sign Out</a></li>

			</div>
		</div>



		<div id="rightWrapper">
			<div id="header">
				<a id="fullPage" href="#">|||</a>
			</div>
			<br> <br> <br>
			<br> <br>
			<div id="description"></div>
			<!--container start-->
			<div id="container">
				<div id="container_body">

					<div>
						<h2 class="form_title">Add Application</h2>
						<p class="head_para">Enter valid details to Add an Application</p>
					</div>
				</div>

				<!--Form  start-->
				<div align="center" id="contents"
					style="margin-left: 20px; margin-right: 20px">

					<form name="appForm" action="saveApplication" method="post"
						onsubmit="return validateApplication()">


						<table style="height: 184px;" width="351" cellspacing="5"
							cellpadding="5">
							<tr>
								<td><label>Application Name:</label></td>
								<td><input type="text" name="applicationName"
									onkeyup="checkAppName()" onblur="validateApplication()"></td>
								<td><div id="appNameError" style="color: red;"
										class="error"></div></td>
							</tr>
							<tr>
								<td><label>Application Type:</label></td>

								<td><input type="text" name="applicationType"
									onkeyup="checkAppType()" onblur="validateApplication()"></td>
								<td><div id="appTypeError" style="color: red;"
										class="error"></div></td>
							</tr>

							<tr>
								<td><label>URL:</label></td>
								<td><input type="text" name="applicationURL"
									onkeyup="checkUrl()" onblur="validateApplication()"></td>
								<td><div id="urlError" style="color: red;" class="error"></div></td>
							</tr>
							<tr>
								<td><label>IP Address:</label></td>
								<td><input type="text" name="internalIpAddress"
									onkeyup="checkIpAddress()" onblur="validateApplication()"></td>
								<td><div id="ipError" style="color: red;" class="error"></div></td>
							</tr>
						</table>
						<br>
						<table style="height: 47px;" width="365">
							<tr>
								<td><button type="submit" class="btn btn-info btn-sm"
										data-toggle="modal" data-target="#myModal">Add
										Application</button></td>
								<td><button type="reset" class="btn btn-info btn-sm"
										data-toggle="modal" data-target="#myModal">Clear</button></td>
							</tr>
						</table>
						<br>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="resources/js/index.js"></script>
</body>
</html>