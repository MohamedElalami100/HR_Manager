$('document').ready(function(){			
	const password = document.getElementById("yourPassword")
	const confirmPassword = document.getElementById("confirmPassword");
	const passwordError = document.getElementById("passwordError");
	
	function validatePassword(){
	  if(password.value != confirmPassword.value) {
	    confirmPassword.setCustomValidity("Passwords Don't Match");
	    passwordError.textContent = "Passwords Don't Match";
	  } else {
	    confirmPassword.setCustomValidity('');
	    passwordError.textContent = "";	    
	  }
	}	
	password.onchange = validatePassword;
	confirmPassword.onkeyup = validatePassword;		
});