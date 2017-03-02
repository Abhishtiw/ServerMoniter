<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <style>
  .error{
color: red;
 font-style: italic;
 font-size: 10px;
}

  
  body {
        background-image: url("../image/45.jpg");
		font-family:Tahoma, Geneva, sans-serif;
} 

#container{
	width:550px;
	background-color:rgba(250,250,252,.9);
	margin:auto;
	margin-top:10px;
	margin-bottom:10px;
	box-shadow:0 0 3px #999;
	}
#container_body{
	padding:20px;
	}
.form_title{
	font-size:35px;
	color:#141823;
	text-align:center;
	padding:10px;
	font-weight:normal;
	}
.head_para{
	font-size:19px;
	color:#99a2a7;
	text-align:center;
	font-weight:normal;
	}

.firstnameorlastname{
	 margin-right:20px;
	}
.input_name{
	width:207px;
	padding:5px;
	font-size:18px;
	}
	.input_num
	{
	width:207px;
	padding:5px;
	font-size:18px;
	}

	.input_number
	{
	width:434px;
	padding:5px;
	font-size:18px;
	}
.input_email{
	width:300px;
	padding:5px;
	font-size:18px;
	}
	
	.comments{
width:400px;
	padding:5px;
	font-size:18px;

select{
	padding:5px;
	}


</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>


function validateApplication() {
	var fv = true;
	var lettersReg = "[a-zA-Z]{3,30}";
	//var urlReg = "/^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/";
	var urlReg="^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$";
	var ipReg = "^(([1-9]?\d|1\d\d|2[0-5][0-5]|2[0-4]\d)\.){3}([1-9]?\d|1\d\d|2[0-5][0-5]|2[0-4]\d)$";

	
 
	var appName = document.appForm.applicationName.value;
	var appType = document.appForm.applicationType.value;
	var url = document.appForm.applicationURL.value;
	var ipaddress = document.appForm.internalIpAddress.value;

    //alert("inside validation");
	if (appName == "" || appName == null) {
		document.getElementById('appNameError').innerHTML = "Application Name cannot be empty";
		fv = false;
	}
	 if (!appName.match(lettersReg)) {
	
		document.getElementById('appNameError').innerHTML = "Application Name cannot have special characters";
	
		fv = false;
		
	} 

	 if (!appType.match(lettersReg)) {
		document.getElementById('appTypeError').innerHTML = "Application Type cannot have special characters";
		fv = false;
	}

	if (appType == "" || appType == null) {
		document.getElementById('appTypeError').innerHTML = "Application Type cannot be empty";
		fv = false;
	}

	if (url == "") {
		document.getElementById('urlError').innerHTML = "URL cannot be empty";
		fv = false;
	}

	if (!url.match(urlReg)) {
		fv = false;
	}else{
		alert("valid url");
		document.appform.applicationURL.focus();
		fv=true;
	}
	
	if (ipaddress == "") {
		document.getElementById('ipError').innerHTML = "ip Address cannot be empty";
		fv = false;
	}
	if (!ipaddress.match(ipReg)) {
		fv = false;
	}else{
		alert("valid ip address");
		document.appform.applicationURL.focus();
		fv=true;
	}

 
	return fv;
}

function checkAppName() {
	document.getElementById('appNameError').innerHTML = "";
}

 function checkAppType() {
	document.getElementById('appTypeError').innerHTML = "";
}

function checkUrl() {
	document.getElementById('urlError').innerHTML = "";
}

function checkIpAddress() {
	document.getElementById('ipError').innerHTML = "";
} 


</script>
</head>

<body>

<div id="description"></div>
<!--container start-->
<div id="container">
  <div id="container_body">
    
    <div>
      <h2 class="form_title">Add Application</h2>
      <p class="head_para">Enter valid details to Add an Application</p>
    </div>
    </div>
    
    <!--Form  start-->
   <div align="center" id="contents" style="margin-left: 20px; margin-right: 20px">
	
		<form name="appForm" action="saveapplication" method="post"> 
			<!-- onsubmit="return validateApplication()" -->
			

				<table style="height: 184px;" width="351" cellspacing="5" cellpadding="5">
					<tr>
						<td>Application Name:<label class="error">*</label></td>
						<td><input type="text" name="applicationName" onkeyup="checkAppName()" onblur="validateApplication()"></td>
						<td><div id="appNameError" class="error"></div></td>
					</tr>
					<tr>
						<td>Application Type:<label class="error">*</td>
			
						<td><input type="text" name="applicationType" onkeyup="checkAppType()" onblur="validateApplication()"></td>
						<td><div id="appTypeError" class="error"></div></td>
					</tr>
					
					<tr>
						<td>URL:<label class="error">*</td>
						<td><input type="text" name="applicationURL" onkeyup="checkUrl()" onblur="validateApplication()"></td>
						<td><div id="urlError" class="error"></div></td>
					</tr>
					<tr>
						<td>IP Address:<label class="error">*</td>
						<td><input type="text" name="internalIpAddress" onkeyup="checkIpAddress()" onblur="validateApplication()"></td>
						<td><div id="ipError" class="error"></div></td>
					</tr>
				</table>
				<br>
				<table  style="height: 47px;" width="365">
				<tr><td><button type="submit" class="btn btn-info btn-lg"
						data-toggle="modal" data-target="#myModal">Add Application </button></td>
			<td><button type="reset" class="btn btn-info btn-lg"
						data-toggle="modal" data-target="#myModal">Clear </button></td></tr>
			</table>
			<br>
		</form>
	</div>
</body>
</html>