<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:Layout>
	<t:NormalSection>
		<div class="p-2 flex-grow flex flex-col">
			<div class="color-5 about-header-container two-column">
				<div class="about-image">
					<img src="images/LogoWhiteCropped.png" alt="Provisio Logo" class="h-32 w-61" />
				</div>

				<h1 class="color-3-text text-2xl baskerville about-title">About us</h1>

			</div>
			<div class="about-content two-column">
				<p>Here at Provisio, we strive to make sure that as a customer, you get the absolute best prices
					we can offer so your traveling needs can always be met. Whether it be for vacations, business trips, or special events,
					we do our utmost to make sure you get the best experience you can afford.</p>

				<p><br>We offer deals for locations across the globe, so that no matter where you need to go, you'll always be able to come to us
					when you need to book a place to stay.</p>
				<p>For more information about our locations, please use the link below:</p>


				<img src="images/SuitcaseStockImage.jpg" alt="Rolling suitcase">

			</div>
		</div>
	</t:NormalSection>
</t:Layout>