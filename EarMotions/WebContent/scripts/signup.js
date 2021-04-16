/**
 * 
 */
function checkUsername(inputtxt) {
	var name = /^[A-Za-z0-9]+$/;;
	if(inputtxt.value.match(name)) 
		return true

	return false;	
}

function checkEmail(inputtxt) {
	var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.value.match(email)) 
		return true;
		
	return false;	
}

function checkPassword(inputtxt1, inputtxt2) {
	if(inputtxt1 === inputtxt2) 
		return true;
		
	return false;
}

function validate(obj) {	
	var valid = true;	
	
	var name = document.getElementsByName("username")[0];
	if(!checkUsername(username)) {
		valid = false;
		name.classList.add("error");
	} else {
		name.classList.remove("error");
	}
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)) {
		valid = false;
		email.classList.add("error");
	} else {
		email.classList.remove("error");
	}
	
	var password = document.getElementsByName("password")[0];
	var confirmpassword = document.getElementsByName("confirmpassword")[0];
	if(!checkPassword(password.value, confirmpassword.value)) {
		valid = false;
		password.classList.add("error");
		confirmpassword.classList.add("error");
	} else {
		password.classList.remove("error");
		confirmpassword.classList.remove("error");
	}
	console.log(valid);
		
	if(valid) obj.submit();
}