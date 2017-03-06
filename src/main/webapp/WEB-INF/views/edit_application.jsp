<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	src="${pageContext.request.contextPath}/resources/js/add_application.js">
	
</script>
</head>

<body bgcolor="rgb(188, 198, 204);">
	<div id="description"></div>
	<!--container start-->
	<div id="container">
		<div id="container_body">

			<div>
				<h2 class="form_title">Application Details</h2>
				<p class="head_para">Enter valid details to update Application</p>
			</div>
		</div>
		<!--Form  start-->
		<div align="center" id="contents"
			style="margin-left: 20px; margin-right: 20px">

			<form name="editAppForm" action="update_application" method="post">
				<!-- onsubmit="return validateApplication()" -->


				<table style="height: 306px;" width="351" cellspacing="5"
					cellpadding="5">
					<tr>
						<td><input type="hidden" name="applicationId"
							value="<c:out value="${application.applicationId}"/>" readonly /></td>
						<td><div id="ipError" class="error"></div></td>
					</tr>
					<tr>
						<td>Application Name:<label class="error">*</label></td>
						<td><input type="text" name="applicationName"
							value="<c:out value="${application.applicationName}"/>" /></td>
						<td><div id="appNameError" class="error"></div></td>
					</tr>
					<tr>
						<td>IP Address:<label class="error">*</label></td>
						<td><input type="text" name="internalIpAddress"
							value="<c:out value="${application.internalIpAddress}"/>" /></td>
						<td><div id="ipError" class="error"></div></td>
					</tr>
					<tr>
						<td>URL:<label class="error">*</label></td>
						<td><input type="text" name="applicationURL"
							value="<c:out value="${application.applicationURL}"/>" /></td>
						<td><div id="urlError" class="error"></div></td>
					</tr>
					<tr>
						<td>Application Type:<label class="error">*</label></td>

						<td><input type="text" name="applicationType"
							value="<c:out value="${application.applicationType}"/>" /></td>
						<td><div id="appTypeError" class="error"></div></td>
					</tr>

					<tr>
						<td><input type="hidden" name="oldStatusCode"
							value="<c:out value="${application.oldStatusCode}"/>" readonly/></td>
						<td><div id="ipError" class="error"></div></td>
					</tr>
					<tr>
						<td><input type="hidden" name="newStatusCode"
							value="<c:out value="${application.newStatusCode}"/>" readonly/></td>
						<td><div id="ipError" class="error"></div></td>
					</tr>
					<tr>
						<td><input type="hidden" name="responseGeneratedTime"
							value="<c:out value="${application.responseGeneratedTime}"/>" readonly/></td>
						<td><div id="ipError" class="error"></div></td>
					</tr>
					<tr>
						<td>E-mail:<label class="error">*</label></td>
						<td><input type="text" name="emailId"
							value="<c:out value="${application.emailId}"/>" /></td>
						<td><div id="ipError" class="error"></div></td>
					</tr>
				</table>
				<br>
				<table style="height: 47px;" width="365">
					<tr>
						<td align="center"><button type="submit"
								class="btn btn-success btn-sm" data-toggle="modal"
								data-target="#myModal">Update</button></td>
						<td align="right"><button type="reset"
								class="btn btn-info btn-sm" data-toggle="modal"
								data-target="#myModal">Go to Menu</button></td>
					</tr>
				</table>
			</form>
		</div>
		</div>
</body>
</html>