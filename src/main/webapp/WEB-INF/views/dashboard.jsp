<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/dashboard.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

<div id="myNav" class="overlay">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <div class="overlay-content">
    <a href="addUser">Register User</a>
    <a href="displayUser">Display user List</a>
    <a href="addApplication">New Application</a>
    <a href="displayApplication">Display application List</a>
    <a href="#">Application Status</a>
    <a href="#">Report</a>
    <a href="#">E-mail History</a>
  </div>
</div>



<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dashboard.js">
</script>
     
</body>
</html>
