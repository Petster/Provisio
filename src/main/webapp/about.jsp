<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:Layout>
	<t:FullSection>
		<c:import url="/About" />
		<div class="flex-grow flex flex-col">
			<div class="flex flex-row p-2 color-5 h-64 content-center items-center justify-center">
				<div class="md:w-2/3 w-64">
					<h1 class="text-3xl baskerville text-white md:ml-4 text-center md:text-start">About Us</h1>
					<div class="border-b-4 border-white md:w-2/3 w-64"></div>
				</div>
				<div class="md:w-1/4"></div>
			</div>
			<div class="flex flex-col-reverse lg:flex-row w-full p-2 justify-center">
				<div class="flex justify-center items-center content-center lg:w-1/2">
					<p>Here at Provisio, we strive to make sure that as a customer, you get the absolute best prices
						we can offer so your traveling needs can always be met. Whether it be for vacations, business trips, or special events,
						we do our utmost to make sure you get the best experience you can afford. We offer deals for locations across the globe, so that no matter where you need to go, you'll always be able to come to us
						when you need to book a place to stay.</p>
				</div>
				<div class="flex justify-center items-center content-center">
					<img src="./images/about1.jpg" alt="Hotel 1" class="p-3" />
				</div>
			</div>

			<div class="flex flex-col-reverse lg:flex-row-reverse w-full p-2 justify-center">
				<div class="flex justify-center items-center content-center lg:w-1/2">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultrices neque ornare aenean euismod elementum nisi quis eleifend quam. Accumsan sit amet nulla facilisi mue id diam vel quam. Quisque sagittis purus sit amet volutpat consequat mauris nunc congue. Ac turpis egestas maecenas pharetra convallis posuere morbi leo. Faucibus pulvinar elementum integer enim neque volutpat. Pharetra vel turpis nunc eget. Sollicitudin aliquam ultrices sagittis orci a.</p>
				</div>
				<div class="flex justify-center items-center content-center">
					<img src="./images/about2.jpg" alt="Hotel 2" class="p-3" />
				</div>
			</div>

			<div class="flex flex-col-reverse lg:flex-row w-full p-2 justify-center">
				<div class="flex justify-center items-center content-center lg:w-1/2">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultrices neque ornare aenean euismod elementum nisi quis eleifend quam. Accumsan sit amet nulla facilisi morbi tempus iaculis urna id. Lorem ipsum dolor sit amet consectetur adipiscing. Mi ipsum faucibus vitae aliqungue. Ac turpis egestas maecenas pharetra convallis posuere morbi leo. Faucibus pulvinar elementum integer enim neque volutpat. Pharetra vel turpis nunc eget. Sollicitudin aliquam ultrices sagittis orci a.</p>
				</div>
				<div class="flex justify-center items-center content-center">
					<img src="./images/about3.jpeg" alt="Hotel 3" class="p-3" />
				</div>
			</div>
			<div class="flex flex-row p-2 color-5 h-64 content-center items-center justify-center">
				<div class="md:w-2/3 w-64">
					<h1 class="text-3xl baskerville text-white md:ml-4 text-center md:text-start">Our Locations</h1>
					<div class="border-b-4 border-white md:w-2/3 w-64"></div>
				</div>
				<div class="md:w-1/4"></div>
			</div>
			<div class="grid gap-4 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 p-2 flex flex-grow justify-center md:w-4/5 md:mx-auto">
				<c:forEach items="${allLocations}" var="i">
					<div class="h-96 w-72 rounded-3xl color-3 flex flex-col flex-grow mx-auto xl:w-full">
						<div class="h-2/5 locationdefault rounded-t-3xl"></div>
						<div class="flex flex-col gap-2 p-2">
							<h1 class="text-center text-2xl baskerville">${i.title}</h1>
							<p class="text-center text-lg baskerville">${i.address}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</t:FullSection>
</t:Layout>