/**
 * 
 */
function validateApplication() {
	var fv = true;
	var lettersReg = /^[A-Za-z]+$/;
	var urlReg=/^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
	var ipReg=/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
		
	var appName = document.appForm.applicationName.value;
	var appType = document.appForm.applicationType.value;
	var url = document.appForm.applicationURL.value;
	var ipaddress = document.appForm.internalIpAddress.value;

	if (appName == "") {
		document.getElementById('appNameError').innerHTML = "Application Name cannot be empty";
		fv = false;
	}
	 if (!appName.match(lettersReg)) {
		document.getElementById('appNameError').innerHTML = "Application Name cannot have special characters";
		fv = false;	
	} 

	if (appType == "") {
		document.getElementById('appTypeError').innerHTML = "Application Type cannot be empty";
		fv = false;
	}

	if (!appType.match(lettersReg)) {
		document.getElementById('appTypeError').innerHTML = "Application Type cannot have special characters";
		fv = false;
	}
	
	if (url == "") {
		document.getElementById('urlError').innerHTML = "URL cannot be empty";
		fv = false;
	}

	if (!url.match(urlReg)) {
		document.getElementById('urlError').innerHTML = "please enter valid URL";
		fv = false;
	}

	if (ipaddress == "") {
		document.getElementById('ipError').innerHTML = "ip Address cannot be empty";
		fv = false;
	}
	if (!ipaddress.match(ipReg)) {
		document.getElementById('ipError').innerHTML = "please enter valid IP address";
		fv = false;
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

