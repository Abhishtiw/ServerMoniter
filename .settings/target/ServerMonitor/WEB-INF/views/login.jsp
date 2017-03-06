<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="container">
		<section id="content">
		<form action="validateLogin" method="post">
			<h1>Login Form</h1>
			<div>
				<input type="text" placeholder="Username" required="" name="userName" />
			</div>
			<div>
				<input type="password" placeholder="Password" required=""
					name="password" />
			</div>
			<div>
				<input type="submit" value="Log in" /> <a href="lost_password">Lost your
					password?</a> <a href="register_user">Register</a>
			</div>
		</form>
		<!-- form --> </section>
		<!-- content -->
	</div>
	<script src="jquery/jquery-2.0.3.min.js"></script>
	<script src="bootstrap/bootstrap-2.3.1.min.js"></script>

</body>
</html>