<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn.email == null}">
	<c:redirect url="/login.jsp"/>
</c:if>
<t:Layout>
	<c:import url="/Reserve" />
	<div class="p-2 flex-grow flex flex-col gap-2">
		<div class="flex flex-col gap-4 md:flex-row w-full color-3 rounded-lg p-2 content-center items-center justify-between ">
			<div class="flex flex-row">
				<input class="hidden" id="datepicker" name="datepicker"/>
			</div>
			<div class="flex flex-col gap-2">
				<div class="flex flex-row">
					<select class="flex flex-grow color-4 color-2-text p-1 text-md border-2 color-1-border" type="text" name="location">
						<c:forEach items="${allLocations}" var="i" varStatus="loop">
							<option value="${loop.getCount()}"><c:out value="${i.address}"></c:out></option>
						</c:forEach>
					</select>
				</div>
				<div class="flex flex-row gap-2 justify-between">
					<select name="filter" class="w-44 color-4 color-2-text p-1 text-md border-2 color-1-border">
						<option>Select an Option</option>
						<option>Price: High to Low</option>
						<option>Price: Low to High</option>
					</select>
					<select name="guests" class="w-44 color-4 color-2-text p-1 text-md border-2 color-1-border">
						<option value="1">1 Guest</option>
						<option value="2">2 Guests</option>
						<option value="3">3 Guests</option>
						<option value="4">4 Guests</option>
						<option value="5">5 Guests</option>
					</select>
				</div>
				<div class="flex flex-row justify-center mx-auto">
					<button class="w-32 color-2-text color-4 p-1 rounded-md border-2 color-1-border">Submit</button>
				</div>
			</div>
		</div>
		<div class="grid grid-cols-1 gap-2 sm:grid-cols-2 xl:grid-cols-3 ">
			<c:forEach items="${allRooms}" var="i" >
				<div price="${i.price}" class="flex flex-row color-3 color-2-hover hover:cursor-pointer w-full rounded-lg">
					<div class="${i.image} rounded-l-lg w-2/5"></div>
					<div class="p-2 w-2/3 flex flex-col">
						<h1 class="text-2xl baskerville color-5-text"><c:out value="${i.title}"></c:out></h1>
						<h2 class="text-lg baskerville color-5-text mt-4">Amenities</h2>
						<div class="border-b-2 border-black"></div>
						<div class="flex flex-row gap-3 mt-1 py-3">
							<c:if test="${i.breakfast == true}">
								<i class="fa fa-coffee fa-xl color-4-text"></i>
							</c:if>
							<c:if test="${i.wifi == true}">
								<i class="fa fa-wifi fa-xl color-5-text"></i>
							</c:if>
							<c:if test="${i.fitness == true}">
								<i class="fa fa-dumbbell fa-xl color-5-text"></i>
							</c:if>
							<c:if test="${i.store == true}">
								<i class="fa fa-store fa-xl color-5-text"></i>
							</c:if>
							<c:if test="${i.noSmoke == true}">
								<i class="fa fa-ban-smoking fa-xl color-5-text"></i>
							</c:if>
							<c:if test="${i.mobile == true}">
								<i class="fa fa-mobile fa-xl color-5-text"></i>
							</c:if>
						</div>
						<h2 class="text-lg baskerville color-5-text">Room Highlights</h2>
						<div class="border-b-2 border-black"></div>
						<div class="flex flex-row justify-between p-2 mt-4 ml-4">
							<c:out value="${i.highlights}"></c:out>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script>
		const picker = new easepick.create({
			element: "#datepicker",
			css: [
				"css/calendar.css"
			],
			zIndex: 10,
			firstDay: 0,
			grid: 2,
			calendars: 2,
			inline: true,
			plugins: [
				"AmpPlugin",
				"RangePlugin"
			]
		})
	</script>
</t:Layout>