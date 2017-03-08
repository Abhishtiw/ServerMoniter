<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	<P>"${message}"</P>
	<a href="addApplication">Register application</a>
	<br>
	<a href="addUser">Add User</a>
	<br>
	<a href="addEmail">Register EmailId</a>
	
	<br>
	<a href="displayApplication">Display application List</a>
	<br>
	<a href="displayUser">Display user List</a>
	<br>
	<a href="testLink">Test code</a>
</body>
</html>
