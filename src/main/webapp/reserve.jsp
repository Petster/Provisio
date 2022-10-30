<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:Layout>
	<div class="p-2 flex-grow flex flex-col">
		<div class="flex flex-row w-full color-3 rounded-lg p-2 content-center items-center justify-between ">
			<div class="flex flex-col">
				<img src="img/LogoSquareTransparent.png" alt="Provisio Logo" class="h-36" />
			</div>
			<div class="flex flex-row">
				<t:Calendar></t:Calendar>
				<div class="flex flex-col color-4-text justify-center items-center content-center px-4">
					<i class="fa-sharp color-4-text fa-solid fa-arrow-right fa-2x"></i>
				</div>
				<t:Calendar></t:Calendar>
			</div>
			<div class="flex flex-col gap-2">
				<div class="flex flex-row">
					<input class="flex flex-grow color-4 color-2-text p-1 text-md border-2 color-1-border" type="text" name="location" placeholder="39th Ave NE Seattle, Washington, 98124" />
				</div>
				<div class="flex flex-row gap-2 justify-between">
					<select name="filter" class="w-44 color-4 color-2-text p-1 text-md border-2 color-1-border">
						<option>Select an Option</option>
						<option>Price: High to Low</option>
						<option>Price: Low to High</option>
					</select>
					<select name="guests" class="w-44 color-4 color-2-text p-1 text-md border-2 color-1-border">
						<option>1 Guest</option>
						<option>2 Guests</option>
						<option>3 Guests</option>
						<option>4 Guests</option>
						<option>5 Guests</option>
					</select>
				</div>
				<div class="flex flex-row justify-center mx-auto">
					<button class="w-32 color-2-text color-4 p-1 rounded-md border-2 color-1-border">Submit</button>
				</div>
			</div>
		</div>
	</div>
</t:Layout>