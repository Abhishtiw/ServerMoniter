<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-mail form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body align="center" bgcolor="rgb(100,110,120)">
<form name="f1" action="mailto:admin@gmail.com" method="get" enctype="multipart/form-data" name="EmailTestForm">
		<h1>E-mail form</h1>
		<table>
			<tr>
				<td>Receiver mail address:</td>
				<td><input type="text" name="to" /> <span id="nameloc"></span></td>
			</tr>
			<tr>
				<td>Sender mail address:</td>
				<td><input type="text" name="from" /></td>
			</tr>
			<tr>
				<td>CC</td>
				<td><input type="text" name="cc" /> <span id="emailloc"></span></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><button type="submit" class="btn btn-info">Send E-mail</button></td>
			</tr>
		</table>
	</form>
</body>
</html>