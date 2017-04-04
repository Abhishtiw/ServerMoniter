/**
 * 
 */
function validateUser() {
	var fv = true;
	var lettersReg = /^[a-zA-Z ]*$/;
	var emailreg = /\S+@\S+\.\S+/;
	
	var firstname = document.registerUser.userName.value;
	var email = document.registerUser.emailId.value;
	var to = document.getElementById('to');
	var cc = document.getElementById('cc');
	var password = document.registerUser.password.value;
	
	if (firstname == "") {
		document.getElementById('firstNameError').innerHTML = "First Name cannot be empty";
		fv = false;
	}

	if (!firstname.match(lettersReg)) {
		document.getElementById('firstNameError').innerHTML = "First Name cannot have special characters";
		fv = false;
	}

	if (email == "") {
		document.getElementById('emailError').innerHTML = "Email cannot be empty";
		fv = false;
	}

	if (!email.match(emailreg)) {
		document.getElementById('emailError').innerHTML = "not a valid email adderss";
		fv = false;
	}
	if ((to.checked == false) && (cc.checked == false)) {
		document.getElementById('emailPropertyError').innerHTML = "Please select either 'To' value or 'CC' value ";
		fv = false;
	}

	if (password == "") {
		document.getElementById('passwordError').innerHTML = "password cannot be empty";
		fv = false;
	}
	return fv;
}

function checkFirstName() {
	document.getElementById('firstNameError').innerHTML = "";
}

function checkLastName() {
	document.getElementById('lastNameError').innerHTML = "";
}

function checkEmail() {
	document.getElementById('emailError').innerHTML = "";
}

function checkIpAddress() {
	document.getElementById('ipError').innerHTML = "";
}
function checkEmailProperty() {
	document.getElementById('emailPropertyError').innerHTML = "";
}
function checkPassword() {
	document.getElementById('passwordError').innerHTML = "";
}
