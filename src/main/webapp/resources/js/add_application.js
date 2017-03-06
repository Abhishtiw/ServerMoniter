/**
 * 
 */
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

