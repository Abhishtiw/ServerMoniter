<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
	<div id="description"></div>
	<!--container start-->
	<div id="container">
		<div id="container_body">

			<div align="right">
					
					<label style="color: red">*</label> mandatory
					</div>
					
			
			<div align="justify">
				<h2 class="form_title">Register User</h2>
				<p class="head_para">Enter valid details to Register</p>
				
			</div>

		</div>
		<!--Form  start-->
	
		<div align="center" id="contents"
			style="margin-left: 20px; margin-right: 20px">

			<form name="registerUser" action="saveUser" method="post">
				<!-- onsubmit="return validateUser()" -->
				<table style="height: 217px;" width="520" cellspacing="5" cellpadding="5">

					
					<tr>
						<td align="center">Name:<label style="color: red">*</label></td>
						<td><input type="text" name="userName"
							onkeyup="checkFirstName()" onblur="validateUser()"></td>
						<td><div id="firstNameError" class="error"></div></td>
					</tr>
					<tr>
						<td align="center">e-mail:<label style="color: red">*</label></td>
						<td><input type="text" name="userMailId" onkeyup="checkEmail()"
							onblur="validateUser()"></td>
						<td><div id="emailError" class="error"></div></td>
					</tr>

					<tr>
						<td align="center">Password:<label style="color: red">*</label></td>
						<td><input type="password" name="password" /></td>
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
								data-target="#myModal">Update</button></td>
						<td><button type="reset" class="btn btn-danger btn-sm"
								data-toggle="modal" data-target="#myModal">Clear</button></td>
					</tr>
						
					</tr>
					
				</table>
				<!-- Modal -->

			</form>
		</div>
</body>
</html>