<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn.email == null}">
	<c:redirect url="/index.jsp"/>
</c:if>
<t:Layout>
	<div class="p-2 flex-grow flex flex-col">
		<h1 class="text-2xl">My Account</h1>
		<c:if test="${sessionScope.LoggedIn.admin == true}">
			<div class="flex flex-row p-2 justify-between items-center content-center mx-auto gap-4">
				<button class="flex flex-grow bg-green-400 p-3 rounded-md" id="resetDatabase">Reset Database</button>
				<button class="flex flex-grow bg-green-500 p-3 rounded-md" id="dummyData">Create Dummy Data</button>
			</div>
		</c:if>
		
		<div class="flex flex-col">
			<h1 class="text-xl">Quick Account Summary</h1>
			<p>ID: <c:out value="${sessionScope.LoggedIn.id}" /></p>
			<p>Firstname: <c:out value="${sessionScope.LoggedIn.firstname}" /></p>
			<p>Lastname: <c:out value="${sessionScope.LoggedIn.lastname}" /></p>
			<p>Password: <c:out value="${sessionScope.LoggedIn.password}" /></p>
			<p>Email: <c:out value="${sessionScope.LoggedIn.email}" /></p>
			<p>Phone #: <c:out value="${sessionScope.LoggedIn.phone}" /></p>
			<p>Join Date: <c:out value="${sessionScope.LoggedIn.joindate}" /></p>
			<p>Admin Status: <c:out value="${sessionScope.LoggedIn.admin}" /></p>
			<p>Loyalty Points: <c:out value="${sessionScope.LoggedIn.loyaltyPoints}" /></p>
		</div>
	</div>
	<script>
		$('#resetDatabase').click(function(e) {
			e.preventDefault();
	    	$.ajax({
				type: 'post',
				url: 'ResetDatabase',
				success: function(response) {
					swal({
					  title: "Success",
					  text: "Database Reset",
					  icon: "success",
					});
				},
				error: function(response) {
					swal({
					  title: "Error",
					  text: "Failed to Reset Database",
					  icon: "error",
					});
				}
			})
	    });
		
		$('#dummyData').click(function(e) {
			e.preventDefault();
	    	$.ajax({
				type: 'post',
				url: 'DummyData',
				success: function(response) {
					swal({
					  title: "Success",
					  text: "Dummy Data Created",
					  icon: "success",
					});
				},
				error: function(response) {
					swal({
					  title: "Error",
					  text: `Failed to Create Dummy Data ${response}`,
					  icon: "error",
					});
				}
			})
	    });
	</script>
</t:Layout>