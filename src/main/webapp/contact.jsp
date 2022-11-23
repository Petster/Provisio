<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:Layout>
	<t:CenteredSection>
	<div class="flex flex-row w-full gap-4 p-4">
		<div class="flex flex-col color-3 p-2 w-2/3 rounded-lg">
			<h1 class="baskerville font-bold color-1-text text-2xl text-center">Send us a message!</h1>
			<div class="border-b-2 color-1-border w-2/3 mx-auto"></div>
			<form class="flex flex-col gap-2 p-4">
				<div class="flex flex-col">
					<label for="name">Your Name</label>
					<input class="rounded-md p-2 text-md" type="text" id="name" placeholder="Enter your name" value="${sessionScope.LoggedIn.firstname} ${sessionScope.LoggedIn.lastname}">
				</div>
				<div class="flex flex-col">
					<label for="email">Your Email</label>
					<input class="rounded-md p-2 text-md" type="email" id="email" placeholder="Enter your email" value="${sessionScope.LoggedIn.email}">
				</div>
				<div class="flex flex-col">
					<label for="phone">Your Phone Number</label>
					<input class="rounded-md p-2 text-md" type="phone" id="phone" placeholder="Enter your phone number" value="${sessionScope.LoggedIn.phone}">
				</div>
				<div class="flex flex-col">
					<label for="resnum">Your Reservation Number</label>
					<input class="rounded-md p-2 text-md" type="text" id="resnum" placeholder="Enter your reservation number" value="">
				</div>
				<div class="flex flex-col">
					<label for="message">Your Message</label>
					<textarea class="resize-none rounded-md p-2 text-md" rows="6" id="message" placeholder="Enter your message"></textarea>
				</div>
				<div class="flex flex-row justify-center items-center content-center gap-4">
					<button class="color-6 flex-grow font-bold text-sm py-1 px-4 rounded-full" type="reset">Reset Form</button>
					<button class="color-2 flex-grow font-bold text-sm py-1 px-4 rounded-full" type="button">Send Email</button>
				</div>
			</form>
		</div>
		<div class="flex flex-col color-3 p-2 w-2/3 rounded-lg">
			<h1 class="baskerville font-bold color-1-text text-2xl text-center">Connect with us!</h1>
			<div class="border-b-2 color-1-border w-2/3 mx-auto"></div>
			<div class="flex flex-row gap-4 content-center items-center justify-center">
				<i class="fa-brands fa-square-youtube fa-3x color-5-text color-4-text-hover cursor-pointer"></i>
				<i class="fa-brands fa-square-snapchat fa-3x color-5-text color-4-text-hover cursor-pointer"></i>
				<i class="fa-brands fa-square-facebook fa-3x color-5-text color-4-text-hover cursor-pointer"></i>
				<i class="fa-brands fa-square-instagram fa-3x color-5-text color-4-text-hover cursor-pointer"></i>
				<i class="fa-brands fa-linkedin fa-3x color-5-text color-4-text-hover cursor-pointer"></i>
				<i class="fa-brands fa-square-twitter fa-3x color-5-text color-4-text-hover cursor-pointer"></i>
			</div>
			<div class="flex flex-col flex-grow content-center items-center justify-center">
				<div class="flex flex-row">
					<div class="flex flex-col w-2/3 p-3">
						<div id="map" class="h-96 w-full"></div>
					</div>
					<div class="flex flex-col w-1/3">
						<h1 class="baskerville font-bold color-1-text text-md text-center">Business Hours</h1>
						<div class="border-b-2 color-1-border w-2/3 mx-auto"></div>
						<p>Mauris consequat sodales dui, a vestibulum ex lobortis non. Curabitur vel suscipit nisi, at hendrerit massa. Praesent id arcu est. Etiam egestas tempus odio.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&callback=initMap&v=weekly" defer></script>
	<script>
		function initMap() {
			// The location of Uluru
			const uluru = { lat: -25.344, lng: 131.031 };
			// The map, centered at Uluru
			const map = new google.maps.Map(document.getElementById("map"), {
				zoom: 4,
				center: uluru,
			});
			// The marker, positioned at Uluru
			const marker = new google.maps.Marker({
				position: uluru,
				map: map,
			});
		}

		window.initMap = initMap;
	</script>
	</t:CenteredSection>
</t:Layout>