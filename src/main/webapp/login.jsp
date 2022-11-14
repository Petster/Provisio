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
		<h1 class="text-2xl text-center color-4-text font-bold p-8">Welcome Back</h1>
		<div class="flex flex-col content-center items-center justify-center color-3 p-8 rounded-lg">
			<form id="loginForm" class="flex flex-col flex-grow w-5/6 gap-4">
				<div class="flex flex-col flex-grow">
					<label for="username" name="username">User Email</label>
					<input class="w-full rounded-md text-md p-2" name="username" type="text" id="username" placeholder="Enter your email"/>
				</div>
				<div class="flex flex-col flex-grow">
					<label for="password" name="password">Password</label>
					<input class="w-full rounded-md text-md p-2" name="password" type="password" id="password" placeholder="Enter your password"/>
					<p class="font-bold text-md">Forgot your password? Click <a class="underline" href="forgot.jsp">Here</a></p>
				</div>
				<div class="flex flex-row justify-between flex-grow">
					<a href="register.jsp" class="color-5 color-2-hover color-2-text color-5-text-hover py-2 px-20 rounded-md font-bold">Register</a>
					<button id="submitLoginUser" class="color-2 color-5-hover color-5-text color-2-text-hover py-2 px-20 rounded-md font-bold">Login</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		$('#submitLoginUser').click(function(e) {
			e.preventDefault();
	    	$.ajax({
				type: 'post',
				url: 'Login',
				data: $('#loginForm').serialize(),
				success: function(response) {
					window.location.href = 'index.jsp';
				},
				error: function(response) {
					swal({
					  title: "Error",
					  text: `Failed to Login (Check username and password) ${response}`,
					  icon: "error",
					});
				}
			})
	    });
	</script>
</t:Layout>