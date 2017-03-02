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
<script>
function validateForm() {
	var result=true;
	var firstname = document.registerUser.firstname.value.trim();
	
	if (firstname == null || firstname == "") {
		alert("Name can't be blank");  
		result=false;
	}
	
	var lastname = document.registerUser.lastname.value;
	if (lastname == null || lastname == "") {
		document.getElementById("errln").innerHTML = "LastName is empty";
		result=false;
	}
	
	
	var email_pattern = /^[A-Za-z0-9]+@[a-z]+.[a-z]+$/;
	var email = document.registerUser.emailid.value;
	var atposition=x.indexOf("@");  
	var dotposition=x.lastIndexOf(".");  
	
	if (atposition<1 || dotposition<atposition+2 || dotposition+2>=x.length){  
 	alert("Please enter a valid e-mail address");  
 	 return false;  
  }  
	return result;
}
</script>

<style>
textarea {
	text-align: top;
}

.error {
	font-style: italic;
	font-weight: bold;
	color: red;
}
</style>

</head>
<body bgcolor="rgb(15,10,20);">
	<h1>Register User</h1>
	<form name="registerUser" action="saveUser" method="post">
		<table>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id"></td>

			</tr>
			<tr>
				<td>First Name:<label style="color: red">*</label></td>
				<td><input type="text" name="userName"></td>

			</tr>
			<!-- <tr>
				<td>Last Name:<label style="color: red">*</label></td>
				<td><input type="text" name="lastname"></td>

			</tr> -->
			<tr>
				<td>email:<label style="color: red">*</label></td>
				<td><input type="text" name="userMailId"></td>

			</tr>

			<!-- <tr>
				<td>Role:</td>
				<td><select name="role">
						<option value="1">Admin</option>
						<option value="2">Others</option>
				</select></td>
			</tr>
			<tr>
			<tr> -->
				<td>
					<button type="submit" class="btn btn-primary">Register
						User</button>
				</td>
			</tr>
			<!-- Modal -->

		</table>
	</form>

</body>
</html>