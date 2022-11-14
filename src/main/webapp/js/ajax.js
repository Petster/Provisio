$('.logout').click(function(e) {
	e.preventDefault();
	$.ajax({
		type: 'post',
		url: 'Logout',
		success: function(response) {
			window.location.href = 'index.jsp';
		},
		error: function(response) {
			swal({
			  title: "Error",
			  text: "Failed to Logout (Uh Oh...)",
			  icon: "error",
			});
		}
	})
});