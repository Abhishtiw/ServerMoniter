<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AHS-Lost Password</title>
<link
	href="${pageContext.request.contextPath}/resources/css/validation.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="resources/js/lost_password.js"></script>
</head>
<body>
	<div id="description"></div>
	<!--container start-->
	<div id="container">
		<div id="container_body">
			<div>
				<h2 class="form_title">Forget Password</h2>
				<p class="head_para">Don't worry!!! Just Type your email
					address. We will send the password to the provided mail id</p>
				<label style="color: red">${UserMessage}</label>
			</div>
		</div>
		<!--Form  start-->
		<div align="center" id="contents"
			style="margin-left: 20px; margin-right: 20px">

			<form name="lostPwdForm" action="get_password" method="post"
				onsubmit="return validateEmail()">
				<table style="height: 184px;" width="351" cellspacing="5"
					cellpadding="5">
					<tr>
						<td align="center"><label>email:</label></td>
						<td><input type="text" name="emailId" onkeyup="checkEmail()"
							onblur="validateEmail()" autofocus /></td>
						<td><span id="emailError" style="color: red;" class="error"></span></td>
					</tr>
				</table>
				<br>
				<table style="height: 47px;" width="365">
					<tr>
						<td align="center"><button type="submit"
								class="btn btn-info btn-sm" data-toggle="modal"
								data-target="#myModal">SUBMIT</button></td>
						<td><button type="reset" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#myModal">CLEAR</button></td>
						<td align="center"><input type="button"
							class="btn btn-info btn-sm" onclick="history.go(-1);return true;"
							value="Back"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>