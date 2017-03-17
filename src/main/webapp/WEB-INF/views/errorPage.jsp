<!-- <html lang="en">
<head>
  <title>Success</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <div class="panel-group">
  

    <div class="panel panel-info">
      <div class="panel-heading">info</div>
      <div class="panel-body">please <a href="/servermonitor/">login</a> to continue.</div>
    </div>

  </div>
</div>

</body>
</html> -->




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

<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/login.js">
	
</script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-7" style="margin-left: 150.828; margin-left: 380px; margin-top: 80px">
    
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-info-sign"></span> Information </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="validateLogin" method="post">
                    
                    
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
                    
                    <div class="form-group">
                        <label for="nopend" class="col-sm-3 control-label">
                          </label>
                            <p>Please <a href="/servermonitor/">Login</a> to Continue </p>
                        <div class="col-sm-9">
                        </div>
                    </div>
                   
                    </div>
                    </form>
                </div>
				
				</div>
        </div>
    </div>
</div>

</body>
</html>