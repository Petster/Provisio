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
		<div class="flex flex-col content-center items-center justify-center color-3 p-8 rounded-xl">
			<form id="userForm" class="flex flex-col flex-grow w-5/6 gap-4">
				<div class="flex flex-row flex-grow gap-4">
					<div class="flex flex-col flex-grow">
						<label for="fname">First Name</label>
						<input required class="w-full rounded-md text-md p-2" name="fname" type="text" id="fname" placeholder="Enter your First Name"/>
					</div>
					<div class="flex flex-col flex-grow">
						<label for="lname">Last Name</label>
						<input required class="w-full rounded-md text-md p-2" name="lname" type="text" id="lname" placeholder="Enter your Last Name"/>
					</div>
				</div>
				<div class="flex flex-row flex-grow gap-4">
					<div class="flex flex-col flex-grow">
						<label for="email">Email</label>
						<input required class="w-full rounded-md text-md p-2" name="email" type="email" id="email" placeholder="Enter your Email"/>
					</div>
					<div class="flex flex-col flex-grow">
						<label for="phone">Phone</label>
						<input required class="w-full rounded-md text-md p-2" name="phone" type="tel" id="phone" placeholder="Enter your Phone Number"/>
					</div>
				</div>
				<div class="flex flex-col flex-grow">
					<label for="password">Password</label>
					<input pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" required class="w-full rounded-md text-md p-2" name="password" type="password" id="password" placeholder="Enter your password"/>
				</div>
				<div class="flex flex-col flex-grow">
					<label for="confirmpassword">Confirm Password</label>
					<input pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" required class="w-full rounded-md text-md p-2" name="confirmpassword" type="password" id="confirmpassword" placeholder="Re-Enter your password"/>
				</div>
				<div class="flex flex-row justify-between flex-grow">
					<a href="login.jsp" class="color-5 color-2-hover color-2-text color-5-text-hover py-2 px-20 rounded-md font-bold">Login</a>
					<button id="submitUserCreate" class="color-2 color-5-hover color-5-text color-2-text-hover py-2 px-20 rounded-md font-bold">Register</button>
				</div>
			</form>
		</div>
	</div>
	<script src="js/formValidation.js"></script>
</t:Layout>