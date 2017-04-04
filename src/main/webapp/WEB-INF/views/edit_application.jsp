<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AHS-Edit Application</title>
<link rel="stylesheet" href="resources/css/style.css">
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
				<ul style="padding-top: -; padding-left: 0px;">
					<li class="list-item-active" style="height: 60px;"><a
						href="applicationstatus"
						style="padding-right: 2px; padding-top: 0px; padding-left: -; border-bottom-width: 3px; padding-bottom: 2px; height: 60px; padding-left: 2px; padding-left: 0px; border-top-width: 0px;"><img
							src="resources/image/estuate.jpg" alt="logo"
							style="padding-left: 0; border-right-width: 5px; padding-right: 5px; width: 215px; height: 60px;"></a></li>
					<li><a href="addApplication">Add Application</a></li>
					<li><a href="addUser">Add User</a></li>
					<li><a href="addIll">Add ILL</a></li>
					<li><a href="displayApplication">View Applications</a></li>
					<li><a href="displayUser">View Users</a></li>
					<li><a href="applicationstatus">Application Status</a></li>
					<li><a href="applicationhealthstatus">Health Status
							History</a></li>
					<li><a href="signout"
						style="border-bottom: solid 1px rgba(0, 0, 0, 0.2);">Sign Out</a></li>
				</ul>
			</div>
		</div>
		<div id="rightWrapper">
			<div id="header" style="border-bottom-width: 1px;">
				<a id="fullPage" href="#">|||</a> <label></label>
			</div>
			<br> <br> <br> <br> <br>
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
								<td><input type="hidden" name="id" autofocus
									value="<c:out value="${application.id}"/>" /></td>
							</tr>
							<tr>
								<td>Application Name:<label class="error">*</label></td>
								<td><input type="text" name="applicationName" autofocus
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
								<td>Set application active :<label class="error">*</label></td>
								<td><select name="active">
										<c:if test="${application.active eq 'true'}">
											<option value="0">No</option>
											<option selected value="1">Yes</option>
										</c:if>
										<c:if test="${application.active eq 'false'}">
											<option selected value="0">No</option>
											<option value="1">Yes</option>
										</c:if>

								</select></td>
								<!-- 	<td><span style="color: red;" id="emailPropertyError"
							class="error"> </span></td> -->
							</tr>

							<tr>
								<td><input type="hidden" name="oldStatusCode"
									value="<c:out value="${application.oldStatusCode}"/>" readonly /></td>
								<td><div id="ipError" class="error"></div></td>
							</tr>
							<tr>
								<td><input type="hidden" name="newStatusCode"
									value="<c:out value="${application.newStatusCode}"/>" readonly /></td>
								<td><div id="ipError" class="error"></div></td>
							</tr>
							<tr>
								<td><input type="hidden" name="responseGeneratedTime"
									value="<c:out value="${application.responseGeneratedTime}"/>"
									readonly /></td>
								<td><div id="ipError" class="error"></div></td>
							</tr>
						</table>
						<br>
						<table style="height: 47px;" width="365">
							<tr>
								<td align="center"><button type="submit"
										class="btn btn-success btn-sm" data-toggle="modal"
										data-target="#myModal">Update</button></td>
								<td align="center"><button class="button button5">Back</button></td>

							</tr>
						</table>
					</form>
				</div>
			</div>
</body>
</html>