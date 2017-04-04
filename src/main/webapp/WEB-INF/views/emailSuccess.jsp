<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AHS-Email Success</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</script>
</head>
<body style="background-color: #d7eef7;">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7"
				style="margin-left: 150.828; margin-left: 380px; margin-top: 80px">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h2>
							<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp; Success
						</h2>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" action="validateLogin"
							method="post">
							<div class="form-group">
								<label for="nopend" class="col-sm-3 control-label"> </label>
								Dear User,<br> <br>Your Password has been sent to the
								provided Email Id.<br> <br> Kindly Check the provided
								Email. <br> <br>Click here to <a
									href="/servermonitor/"><strong>Login</strong></a>
								<div class="col-sm-9"></div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>