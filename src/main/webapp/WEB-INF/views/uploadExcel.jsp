<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Enter Excel file location and sheet name</h1>
	<form method="post" action="upload">
		<table cellspacing="5"
			cellpadding="5">
			<tr>
				<td><label>File location:</label></td>
				<td><input type="text" name="filepath" /></td>
			</tr>
			<tr>
			<td><label>Sheet name:</label></td>
				<td><input type="text" name="sheet" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="select" /></td>
			</tr>
		</table>
	</form>
</body>
</html>