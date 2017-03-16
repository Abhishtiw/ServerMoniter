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

<link href="${pageContext.request.contextPath}/resources/css/login3.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/login3.js">
	
</script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-7" style="margin-left: 150.828; margin-left: 380px; margin-top: 80px">
    
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-lock"></span> Login</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="validateLogin" method="post">
                    <div class="form-group">
                        <label for="nokp" class="col-sm-3 control-label">
                            Username</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nokp" placeholder="Username" name="userName" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nopend" class="col-sm-3 control-label">
                            Password</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="nopend" placeholder="Password" name="password" required>
                        </div>
                    </div>
                   <!--  <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"/>
                                    Remember me
                                </label>
                            </div>
                        </div>
                    </div> -->
                    <div class="form-group last">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-success btn-sm">
                                Sign in</button>
                                 <button type="reset" class="btn btn-default btn-sm">
                                Reset</button>
                        </div>
                    </div>
                    </form>
                </div>
					<div class="panel-footer">
						Not Registred? <a href="signUp">Register here</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <!-- <a href="lost_password">Lost
							Password</a> -->
					</div>


				</div>
        </div>
    </div>
</div>

</body>
</html>