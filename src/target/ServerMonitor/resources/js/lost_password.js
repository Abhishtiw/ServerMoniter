function validateUser() {
	var fv = true;
	var email = document.registerUser.email.value;
		
	var atposition=email.indexOf("@");  
	var dotposition=email.lastIndexOf(".");  
	
	if (atposition<1 || dotposition<atposition+2 || dotposition+2>=email.length){  
 	alert("Please enter a valid e-mail address example:abc@gmail.com"); 
 	document.getElementById('emailError').innerHTML = "Please enter valid e-mail address "; 
 	 fv= false;  
  }  
	return fv;
}


function checkEmail() {
	document.getElementById('emailError').innerHTML = "";
}

