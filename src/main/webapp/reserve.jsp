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
					<select class="flex flex-grow color-4 color-2-text p-1 text-md border-2 color-1-border" type="text" id="location" name="location">
						<option value="0">Select a Location</option>
						<c:forEach items="${allLocations}" var="i" varStatus="loop">
							<option value="${loop.getCount()}"><c:out value="${i.address}"></c:out></option>
						</c:forEach>
					</select>
				</div>
				<div class="flex flex-row gap-2 justify-between">
					<select id="filter" name="filter" class="w-44 color-4 color-2-text p-1 text-md border-2 color-1-border">
						<option value="">Select an Option</option>
						<option value="htl">Price: High to Low</option>
						<option value="lth">Price: Low to High</option>
					</select>
					<select id="guests" name="guests" class="w-44 color-4 color-2-text p-1 text-md border-2 color-1-border">
						<option value="1">1 Guest</option>
						<option value="2">2 Guests</option>
						<option value="3">3 Guests</option>
						<option value="4">4 Guests</option>
					</select>
				</div>
				<div class="flex flex-row justify-center mx-auto">
					<button id="filterSubmit" class="w-32 color-2-text color-4 p-1 rounded-md border-2 color-1-border">Submit</button>
				</div>
			</div>
		</div>
		<div id="roomWrapper" class="grid grid-cols-1 gap-2 sm:grid-cols-2 xl:grid-cols-3 ">
			<c:forEach items="${allRooms}" var="i" >
				<div loyalty="${i.loyaltyPoints}" roomType="${i.ID}" price="${i.price}" title="${i.title}" image="${i.image}" desc="${i.highlights}" amenities="${i.breakfast},${i.wifi},${i.fitness},${i.store},${i.noSmoke},${i.mobile}" class="roomItem flex flex-row color-3 color-2-hover hover:cursor-pointer w-full rounded-lg">
					<div class="${i.image} rounded-l-lg w-2/5"></div>
					<div class="p-2 w-2/3 flex flex-col">
						<h1 class="text-2xl baskerville color-5-text"><c:out value="${i.title}"></c:out></h1>
						<h2 class="text-lg baskerville color-5-text mt-4">Amenities</h2>
						<div class="border-b-2 border-black"></div>
						<div class="flex flex-row gap-3 mt-1 py-3">
							<c:if test="${i.breakfast == true}">
								<i class="fa fa-coffee fa-xl color-5-text"></i>
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
	<div id="modalLayer" class="hidden p-2 absolute flex flex-col items-center justify-center content-center w-full h-full gap-2 z-50 bg-black backdrop-filter backdrop-blur-md bg-opacity-40">

	</div>
	<script>
		const minDate = new Date();
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
			LockPlugin: {
				minDate: minDate,
				selectForward: false,
				selectBackward: false
			},
			plugins: [
				"RangePlugin",
				"LockPlugin"
			]
		})

		$('#filterSubmit').click(() => {
			switch(document.getElementById("filter").value) {
				case '':
					break;
				case 'htl':
					sortHTL();
					break;
				case 'lth':
					sortLTH();
					break;
			}

		})

		const sortHTL = () => {
			$('#roomWrapper').find('.roomItem').sort((a, b) => {
				return $(b).attr('price') - $(a).attr('price');
			}).appendTo($('#roomWrapper'));
		}

		const sortLTH = () => {
			$('#roomWrapper').find('.roomItem').sort((a, b) => {
				return $(a).attr('price') - $(b).attr('price');
			}).appendTo($('#roomWrapper'));
		}

		$('.roomItem').click(function() {
			let price = $(this).attr('price');
			let title = $(this).attr('title');
			let desc = $(this).attr('desc');
			let image = $(this).attr('image');
			let id = $(this).attr('roomType');
			let loyalty = $(this).attr('loyalty');
			let amenities = $(this).attr('amenities').split(',');
			let location = document.getElementById('location').value;
			let guests = document.getElementById('guests').value;

			$('#modalLayer').toggleClass('hidden');
			$('#modalLayer').append(`
				<div class="roomItem sm:h-96 flex flex-row color-3 sm:w-3/4 rounded-lg">
					<div class="`+image+` rounded-l-lg w-2/5"></div>
					<div class="p-2 w-2/3 flex flex-col">
						<div class="flex flex-row justify-between content-center items-center">
							<h1 class="text-2xl baskerville color-5-text">`+title+`</h1>
							<button id="closeModal" class="color-5-text color-4-text-hover"><i class="fa-regular fa-circle-xmark fa-xl"></i></button>
						</div>
						<h2 class="text-lg baskerville color-5-text mt-4">Amenities</h2>
						<div class="border-b-2 border-black"></div>
						<div id="amenities" class="flex flex-row gap-3 mt-1 py-3">
						</div>
						<h2 class="text-lg baskerville color-5-text">Room Highlights</h2>
						<div class="border-b-2 border-black"></div>
						<div class="flex flex-row justify-between p-2 mt-4 ml-4">
							`+desc+`
						</div>
						<div class="flex flex-col flex-grow"></div>
						<div class="flex flex-row justify-between content-center items-center">
							<p class="text-2xl font-bold color-4-text">$`+price+`/night <span class="text-sm">`+loyalty+` Loyalty Points</span></p>
							<button id="confirmPurchase" class="font-bold color-4 color-2-text color-2-hover color-4-text-hover rounded-md p-2">Reserve Room</button>
						</div>
					</div>
				</div>
			`);

			for(let i = 0; i < amenities.length; i++) {
				if(amenities[i] == 'true') {
					switch(i) {
						case 0:
							$('#amenities').append(`<i class="fa fa-coffee fa-xl color-5-text"></i>`)
							break;
						case 1:
							$('#amenities').append(`<i class="fa fa-wifi fa-xl color-5-text"></i>`)
							break;
						case 2:
							$('#amenities').append(`<i class="fa fa-dumbbell fa-xl color-5-text"></i>`)
							break;
						case 3:
							$('#amenities').append(`<i class="fa fa-store fa-xl color-5-text"></i>`)
							break;
						case 4:
							$('#amenities').append(`<i class="fa fa-ban-smoking fa-xl color-5-text"></i>`)
							break;
						case 5:
							$('#amenities').append(`<i class="fa fa-mobile fa-xl color-5-text"></i>`)
							break;
					}
				}
			}

			$('#closeModal').click(function() {
				$('#modalLayer').toggleClass('hidden').empty();
			})

			$('#confirmPurchase').click(function(e) {
				e.preventDefault();
				try {
					let datepicker = document.getElementById('datepicker').value.split(' - ');
					if(datepicker[0] === '') {
						throw new Error("You must select a date before reserving a room");
					}
					let d1 = new Date(datepicker[0]);
					let d2 = new Date(datepicker[1]);

					let diftime = d2.getTime() - d1.getTime();
					let difday = diftime / (1000 * 3600 * 24);
					let finalData = "roomType=" + id + "&fromDate=" + datepicker[0] + "&toDate=" + datepicker[1] + "&price=" + difday*price + "&location=" + location + "&guests=" + guests + "&loyalty=" + loyalty;

					if(location == 0) {
						throw new Error("Please select a Location");
					}

					swal({
						title: "Reservation Confirmation",
						text: "Confirm Reservation for " + title + " for $" + difday*price + " From: " + datepicker[0] + " To: " + datepicker[1] + " For " + guests + " Guests?",
						icon: "info",
						buttons: ['Cancel', 'Confirm Reservation']
					}).then((result) => {
						if(result) {
							$.ajax({
								type: 'post',
								url: 'Reserve',
								data: finalData
							}).then((result) => {
								if(result.success) {
									swal({
										title: "Success",
										text: result.msg,
										icon: "success",
									}).then(() => {
										window.location.href = 'myaccount.jsp';
									})
								} else {
									throw new Error(result.msg);
								}
							})
						}
					})

				} catch (e) {
					swal({
						title: "Error",
						text: e.message,
						icon: "error",
					});
				}
			})
		})
	</script>
</t:Layout>