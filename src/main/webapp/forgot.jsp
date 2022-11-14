<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn.email != null}">
	<c:redirect url="/index.jsp"/>
</c:if>
<t:Layout>
	<div class="p-2 flex-grow flex flex-col">
		<h1 class="text-2xl text-center color-4-text font-bold p-8">Password Reset</h1>
		<div class="flex flex-col content-center items-center justify-center color-3 p-8 rounded-lg">
			<form id="loginForm" class="flex flex-col flex-grow w-5/6 gap-4">
				<div class="flex flex-col flex-grow">
					<label for="username" name="username">User Email</label>
					<input class="w-full rounded-md text-md p-2" name="username" type="text" id="username" placeholder="Enter your email"/>
				</div>
				<div class="flex flex-col flex-grow">
					<label for="newpassword" name="newpassword">New Password</label>
					<input class="w-full rounded-md text-md p-2" name="newpassword" type="password" id="newpassword" placeholder="Enter your password"/>
				</div>
				<div class="flex flex-col flex-grow">
					<label for="confirmnewpassword" name="confirmnewpassword">Confirm New Password</label>
					<input class="w-full rounded-md text-md p-2" name="confirmnewpassword" type="password" id="confirmnewpassword" placeholder="Enter your password"/>
				</div>
				<div class="flex flex-row justify-center flex-grow">
					<button id="submitResetUser" class="color-2 color-5-hover color-5-text color-2-text-hover py-2 px-20 rounded-md font-bold">Reset Password</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		$('#submitResetUser').click(function(e) {
			e.preventDefault();
	    	$.ajax({
				type: 'post',
				url: 'Forgot',
				data: $('#loginForm').serialize()
			}).then((result) => {
				if(result.success) {
					swal({
					  title: "Success",
					  text: result.msg,
					  icon: "success",
					}).then(() => {
						window.location.href = 'index.jsp';
					});
				} else {
					swal({
					  title: "Error",
					  text: result.msg,
					  icon: "error",
					});
				}
			})
	    });
	</script>
</t:Layout>