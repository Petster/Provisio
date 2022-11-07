"use strict";

let validInputs = [
	false,
	false,
	false,
	false,
	false,
	false
];

let canSubmit = false;

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
	console.log(validInputs);
})

$('#submitUserCreate').click(function(e) {
		e.preventDefault();
		for(let i = 0; i < validInputs.length; i++) {
			if(validInputs[i] === false) {
				canSubmit = false;
				swal({
				  title: "Error",
				  text: `${formInputs[i].name} is Empty`,
				  icon: "error",
				})
			} else if (i >= 5) {
				canSubmit = true;
			}
		}
		if(canSubmit) {
			if($('#password').val() === $('#confirmpassword').val()) {
	     		$.ajax({
					type: 'post',
					url: 'Register',
					data: $('#userForm').serialize(),
					success: function(response) {
						swal({
						  title: "Success",
						  text: "Account Created",
						  icon: "success",
						}).then(function() {window.location.href = 'login.jsp'});
					},
					error: function(response) {
						swal({
						  title: "Error",
						  text: "Failed to Create Account Try Again",
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