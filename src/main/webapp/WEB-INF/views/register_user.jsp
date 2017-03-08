<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style1.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link
	href="${pageContext.request.contextPath}/resources/css/add_application.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/add_user.js">
	
</script>

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
			
			<br>
  <br>
  <br><br>
  <br>
			
			<div id="description"></div>
			<!--container start-->
			<div id="container">
				<div id="container_body">



					<div align="justify">
						<h2 class="form_title">Register User</h2>
						<p class="head_para">Enter valid details to Register</p>

					</div>

				</div>
				<!--Form  start-->

				<div align="center" id="contents"
					style="margin-left: 20px; margin-right: 20px">

					<form name="registerUser" action="saveUser" method="post"
						onsubmit="return validateUser()">
						<table style="height: 217px;" width="520" cellspacing="5"
							cellpadding="5">


							<tr>
								<td align="center"><label>Name:</label></td>
								<td><input type="text" name="userName"
									onkeyup="checkFirstName()" onblur="validateUser()"></td>
								<td><span style="color: red;" id="firstNameError"
									class="error"> </span></td>
							</tr>
							<tr>
								<td align="center"><label>e-mail:</label></td>
								<td><input type="text" name="emailId"
									onkeyup="checkEmail()" onblur="validateUser()"></td>
								<td><span style="color: red;" id="emailError" class="error">
								</span></td>
							</tr>
							<tr>
								<td align="center"><label>Use EmailId as :</label></td>
								<td><input type="radio" id="to" name="mail" value="to"
									onclick="checkEmailProperty()" onblur="validateUser()" /> To<br>
									<input type="radio" id="cc" name="mail" value="cc"
									onclick="checkEmailProperty()" onblur="validateUser()" /> cc<br></td>
								<td><span style="color: red;" id="emailPropertyError"
									class="error"> </span></td>
							</tr>
							<tr>
								<td align="center"><label>Password:</label></td>
								<td><input type="password" name="password"
									onkeyup="checkPassword()" onblur="validateUser()" /></td>
								<td><span style="color: red;" id="passwordError"
									class="error"> </span></td>
							</tr>

							<!-- <tr>
						<td align="center">Role:<label style="color: red">*</label></td>
						<td><select name="role">
								<option value="1">Admin</option>
								<option value="2">Others</option>
						</select></td>
					</tr> -->

						</table>
						<br>
						<table style="height: 47px;" width="365">
							<tr>
								<td align="center"><button type="submit"
										class="btn btn-info btn-sm" data-toggle="modal"
										data-target="#myModal">Register</button></td>
								<td><button type="reset" class="btn btn-danger btn-sm"
										data-toggle="modal" data-target="#myModal">Clear</button></td>
							</tr>



						</table>
						<!-- Modal -->

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