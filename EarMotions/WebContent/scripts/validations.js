/**
 * 
 */

function checkNamesurname(inputtxt) {
	var name = /^[A-Za-z]+$/;
	if(inputtxt.value.match(name)) 
		return true

	return false;	
}

function checkCap(inputtxt) {
	var cap = /^[0-9]{5,8}$/;
	if(inputtxt.value.match(cap)) 
		return true

	return false;	
}

function checkEmail(inputtxt) {
	var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.value.match(email)) 
		return true;
	
	return false;	
}

function checkPhonenumber(inputtxt) {
	var phoneno = /^([0-9]{2,4}-?[0-9]{7,10})$/;
	if(inputtxt.value.match(phoneno)) 
		return true;
	
	return false;
}


function validate(obj) {	
	var valid = true;	
	
	var name = document.getElementsByName("firstname")[0];
	if(!checkNamesurname(name)) {
		valid = false;
		name.classList.add("error");
	} else {
		name.classList.remove("error");
	}
	
	var surname = document.getElementsByName("lastname")[0];
	if(!checkNamesurname(surname)) {
		valid = false;
		surname.classList.add("error");
	} else {
		surname.classList.remove("error");
	}
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)) {
		valid = false;
		email.classList.add("error");
	} else {
		email.classList.remove("error");
	}	
	
	var numbers = document.getElementsByName("number");
	for(var i=0; i < numbers.length; i++) {
		if(!checkPhonenumber(numbers[i])) {
			valid = false;
			numbers[i].classList.add("error");
		} else  {
			numbers[i].classList.remove("error");
		}
	}	
	
	var provincia = document.getElementsByName("provincia")[0];
	if(provincia.options[provincia.selectedIndex].value == "") {
		valid = false;
		provincia.classList.add("error");
	} else {
		provincia.classList.remove("error");
	} 
	
	if(valid) obj.submit();
}