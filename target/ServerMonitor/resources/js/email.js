/**
 * 
 */
function validateEmail() {
	var fv = true;
	var lettersReg = "[a-zA-Z]{3,30}";
	
	var receiverMail = document.sendMail.to.value;
	var senderMail = document.sendMail.send.value;
	var ccMail = document.sendMail.cc.value;
	
		
	var atposition=receiverMail.indexOf("@");  
	var dotposition=receiverMail.lastIndexOf(".");  
	
	if (atposition<1 || dotposition<atposition+2 || dotposition+2>=receiverMail.length){  
 	alert("Please enter a valid e-mail address example:abc@gmail.com"); 
 	document.getElementById('emailError').innerHTML = "Please enter valid e-mail address "; 
 	 fv= false;  
  }  
	return fv;
}


function checkEmail() {
	document.getElementById('emailError').innerHTML = "";
}

