"use strict";

let validInputs = [
	false,
	false,
	false,
	false,
	false,
	false
];

let form = document.getElementById('userForm');

let formInputs = form.querySelectorAll('input');

form.addEventListener('keyup', function(e) {
	e.preventDefault();
	for(let i = 0; i < formInputs.length; i++) {
		if(formInputs[i].value === '') {
			validInputs[i] = false;
		} else {
			validInputs[i] = true;
		}
	}
})

$('#submitUserCreate').click(function(e) {
		e.preventDefault();
		if(validInputs.includes(false)) {
			for(let i = 0; i < validInputs.length; i++) {
			if(validInputs[i] === false) {
				let fieldName = ""
				switch(formInputs[i].name) {
					case "fname": fieldName = "First Name"; break;
					case "lname": fieldName = "Last Name"; break;
					case "email": fieldName = "Email"; break;
					case "phone": fieldName = "Phone Number"; break;
					case "password": fieldName = "Password"; break;
					case "confirmpassword": fieldName = "Confirm Password"; break;
				}
				swal({
				  title: "Error",
				  text: `The ${fieldName} field is empty`,
				  icon: "error",
				});
				break;
			}
			}
		} else {
			if($('#password').val() === $('#confirmpassword').val()) {
	     		$.ajax({
					type: 'post',
					url: 'Register',
					data: $('#userForm').serialize(),
				}).then((results) => {
					if(results.success) {
						swal({
						  title: "Success",
						  text: "Account Created",
						  icon: "success",
						}).then(function() {window.location.href = 'login.jsp'});
					} else {
						swal({
						  title: "Error",
						  text: `${results.msg}`,
						  icon: "error",
						});
					}
				})
	     	} else {
	     		swal({
					  title: "Error",
					  text: "Password's do not match",
					  icon: "error",
					});
	     	}    
		}
     });