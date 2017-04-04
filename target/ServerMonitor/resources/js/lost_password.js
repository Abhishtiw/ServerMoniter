function validateEmail() {
	
	var emailreg = /\S+@\S+\.\S+/;
	
	var email = document.lostPwdForm.emailId.value;
	
	if (email == "") {
		document.getElementById('emailError').innerHTML = "Email cannot be empty";
		fv = false;
	}

	if (!email.match(emailreg)) {
		document.getElementById('emailError').innerHTML = "not a valid email adderss";
		fv = false;
	}
}	
	function checkEmail() {
	document.getElementById('emailError').innerHTML = "";

}