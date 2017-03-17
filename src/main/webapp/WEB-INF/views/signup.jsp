<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style1.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link
	href="${pageContext.request.contextPath}/resources/css/signup.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/add_user.js">
	
</script>
</head>
<body bgcolor="#e6ffff">
	<div class="container-fluid">
		<div class="container-fluid video">
			<!-- <video poster="#" id="bgvid" playsinline autoplay muted loop>
	
	<source src="https://drive.google.com/uc?export=download&id=0B-ldJJbw0AnkTmZCT0E1S0pwZFk" type="video/mp4">
	</video> -->
		</div>
		<div class="container-fluid">
			<div class="row" style="background-color: #efeff5;height: 1200px ">
				<div class="logo text-center" style="margin-top: 10px;
    margin-left: 11px;">
					<a href="#"><img src="resources/image/logo.PNG"
						alt="logo"
						style="padding-left: 0; border-right-width: 5px; padding-right: 5px; width: 215px; height: 66.15px"></a>
				</div>
				<div class="headText text-center col-sm-6">
					<h1 style="font-family: Comic Sans MS, cursive, sans-serif">Estuate Software Pvt Ltd</h1>
				</div>
				<div id="polina" class="col-sm-6" style="background: rgba(123, 167, 193, 0.61) ">
					<h4 class="text-center" style="color: #243a65">Register as a New User</h4>
					<div class="social text-center">
						<div class="row" style="background-color: #efeff5">
							<!-- 	<div class="col-sm-6">
        <a class="btn btn-block btn-social btn-facebook" onclick="_gaq.push(['_trackEvent', 'btn-social', 'click', 'btn-facebook']);">
            <span class="fa fa-facebook"></span> Sign Up
        </a>
    </div>
    <div class="col-sm-6">
    <a class="btn btn-block btn-social btn-google" onclick="_gaq.push(['_trackEvent', 'btn-social', 'click', 'btn-google']);">
        <span class="fa fa-google"></span> Sign up
    </a>
    </div>
	</div> -->
						</div>
						<div class="text-center">
							<h5></h5>
						</div>
						<form class="form-horizontal"name="registerUser" action="registerMe" method="post"
						onsubmit="return validateUser()">
							<div class="form-group has-success has-feedback">
								<div class="col-sm-12">
									<input type="text" class="form-control" id="inputSuccess"
										placeholder="Name" required name="userName"
										onkeyup="checkFirstName()" onblur="validateUser()"> <span
										class="glyphicon glyphicon-user form-control-feedback"></span>
										<span style="color: red;" id="firstNameError"
									class="error"> </span>
								</div>
							</div>
							<div class="form-group has-success has-feedback">
								<div class="col-sm-12">
									<input type="text" class="form-control" id="inputSuccess"
										placeholder="Email" required name="emailId"
										onkeyup="checkEmail()" onblur="validateUser()"> <span
										class="glyphicon glyphicon-envelope form-control-feedback"></span>
										<span style="color: red;" id="emailError" class="error">
								</span>
								</div>
							</div>
							<div class="form-group has-success has-feedback">
								<div class="col-sm-12"
									style="padding-left: 5px; padding-right: 110px;">
									<label style="color: #243a65">Use Email As : </label>&nbsp;&nbsp;&nbsp;&nbsp; <label style="color: #243a65">&nbsp;TO
										&nbsp;&nbsp; </label><input type="radio" id="to" id="inputSuccess"
										name="mail" value="to" onclick="checkEmailProperty()"
										onblur="validateUser()" style="height: 20px; width: 20px;" />&nbsp;&nbsp;
								</div>
								<br>
								<div>
								<label style="color: #243a65">&nbsp;&nbsp;CC &nbsp;&nbsp;</label><input type="radio"
									id="inputSuccess" id="cc" name="mail" value="cc"
									onclick="checkEmailProperty()" onblur="validateUser()"
									style="height: 20px; width: 20px;" /> <!-- <span
									class="glyphicon glyphicon-phone form-control-feedback"></span> -->
							</div>
					</div>
					<div class="form-group has-success has-feedback">
						<div class="col-sm-12">
							<input type="text" class="form-control" id="inputSuccess"
								placeholder="Password" required name="password"
									onkeyup="checkPassword()" onblur="validateUser()"> <span
								class="glyphicon glyphicon-lock form-control-feedback"></span>
								<span style="color: red;" id="passwordError"
									class="error">
						</div>
					</div>
					<br>
					<div class="text-center">
						<!-- <p class="text-center">
							By signing up, you agree to our <a href="#">T&C</a> and <a
								href="#">privacy policy</a>
						</p> -->
						<!-- <button type="button" class="btn sellBtn btn-success">START
							SELLING</button> -->
	&nbsp;&nbsp;&nbsp;&nbsp;<div class="col-sm-6">
        <a class="btn btn-block btn-social btn-facebook" onclick="return confirm('Please Check all the fields!!')">
            <span class="fa fa-sign-in"></span> Sign Up
        </a>
        <a class="btn btn-block btn-social btn-facebook" href="/servermonitor/">
           <!--  <span class="fa fa-sign-in"></span> --> Back
        </a>
					</div>
					</div>
					</form>
					<br>
					<!-- <div class="text-right">
						<p>
							Already Registered?<a href="#"> Login Here </a>
						</p>
					</div> -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>