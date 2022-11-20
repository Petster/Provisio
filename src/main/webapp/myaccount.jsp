<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn.email == null}">
	<c:redirect url="/index.jsp"/>
</c:if>
<t:Layout>
	<c:import url="/MyAccount" />
	<div class="flex flex-row w-full gap-4 p-4">
		<div id="roomsList" class="flex flex-col color-3 p-2 w-1/2 rounded-lg overflow-y-auto newsbox" style="max-height: 600px">
			<h1 class="text-start text-2xl font-bold  color-4-text baskerville">Reservations</h1>
			<div class="border-b-2 color-4-border w-2/4"></div>
			<div id="roomWrapper" class="flex flex-col gap-3 mt-2">
				<c:forEach items="${myReservations}" var="i" >
					<c:forEach items="${allRooms}" var="k">
						<c:if test="${i.roomType == k.ID}">
							<div reservationid="${i.id}" reservedate="${i.reserveDate}" fromDate="${i.fromDate}" toDate="${i.toDate}" roomType="${k.ID}" overallPrice="${i.price}" price="${k.price}" title="${k.title}" image="${k.image}" desc="${k.highlights}" amenities="${k.breakfast},${k.wifi},${k.fitness},${k.store},${k.noSmoke},${k.mobile}" class="roomItem flex flex-row color-2 hover:cursor-pointer w-full rounded-lg">
								<div class="${k.image} rounded-l-lg w-2/5"></div>
								<div class="p-2 w-2/3 flex flex-col">
									<h1 class="text-2xl baskerville color-5-text"><c:out value="${k.title}"></c:out></h1>
									<h2 class="text-lg baskerville color-5-text mt-4">Amenities</h2>
									<div class="border-b-2 border-black"></div>
									<div class="flex flex-row gap-3 mt-1 py-3">
										<c:if test="${k.breakfast == true}">
											<i class="fa fa-coffee fa-xl color-5-text"></i>
										</c:if>
										<c:if test="${k.wifi == true}">
											<i class="fa fa-wifi fa-xl color-5-text"></i>
										</c:if>
										<c:if test="${k.fitness == true}">
											<i class="fa fa-dumbbell fa-xl color-5-text"></i>
										</c:if>
										<c:if test="${k.store == true}">
											<i class="fa fa-store fa-xl color-5-text"></i>
										</c:if>
										<c:if test="${k.noSmoke == true}">
											<i class="fa fa-ban-smoking fa-xl color-5-text"></i>
										</c:if>
										<c:if test="${k.mobile == true}">
											<i class="fa fa-mobile fa-xl color-5-text"></i>
										</c:if>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
		<div class="flex flex-col w-1/2 gap-4">
			<div class="flex flex-col color-3 p-4 flex-grow rounded-lg">
				<h1 class="text-start text-2xl font-bold  color-4-text baskerville">Loyalty Rewards</h1>
				<div class="border-b-2 color-4-border w-3/5"></div>
				<p>Loyalty Points: <c:out value="${sessionScope.LoggedIn.loyaltyPoints}" /></p>
			</div>
			<div class="flex flex-col color-3 p-4 flex-grow rounded-lg">
				<h1 class="text-start text-2xl font-bold  color-4-text baskerville">Account Details</h1>
				<div class="border-b-2 color-4-border w-3/5"></div>
				<div class="flex flex-row justify-between text-lg font-bold">
					<p>ID:</p>
					<c:out value="${sessionScope.LoggedIn.id}" />
				</div>
				<div class="flex flex-row justify-between text-lg font-bold">
					<p>First Name:</p>
					<c:out value="${sessionScope.LoggedIn.firstname}" />
				</div>
				<div class="flex flex-row justify-between text-lg font-bold">
					<p>Last Name:</p>
					<c:out value="${sessionScope.LoggedIn.lastname}" />
				</div>
				<div class="flex flex-row justify-between text-lg font-bold">
					<p>Phone Number:</p>
					<c:out value="${sessionScope.LoggedIn.phone}" />
				</div>
				<div class="flex flex-row justify-between text-lg font-bold">
					<p>Email:</p>
					<c:out value="${sessionScope.LoggedIn.email}" />
				</div>
				<div class="flex flex-row justify-between text-lg font-bold">
					<p>Member Since:</p>
					<c:out value="${sessionScope.LoggedIn.joinDate}" />
				</div>
				<div class="flex flex-row justify-between text-lg font-bold">
					<p>Admin Status:</p>
					<c:out value="${sessionScope.LoggedIn.admin}" />
				</div>
			</div>
		</div>
	</div>
	<div id="modalLayer" class="hidden p-2 absolute flex flex-col items-center justify-center content-center w-full h-full gap-2 z-50 bg-black backdrop-filter backdrop-blur-md bg-opacity-40">

	</div>
	<script>
		let follow = document.getElementById('mainSection').offsetHeight;

		window.onload = (event) => {
			calculateHeight();
		}

		const calculateHeight = () => {
			let follow = document.getElementById('mainSection').offsetHeight;
			let rooms = document.getElementById('roomsList');
			rooms.style.maxHeight = follow + "px";
		}

		$('.roomItem').click(function() {
			let price = $(this).attr('price');
			let title = $(this).attr('title');
			let desc = $(this).attr('desc');
			let image = $(this).attr('image');
			let id = $(this).attr('roomType');
			let fromDate = $(this).attr('fromDate');
			let toDate = $(this).attr('toDate');
			let reserveDate = $(this).attr('reserveDate');
			let reservationid = $(this).attr('reservationid');
			let overallPrice = $(this).attr('overallPrice');
			let amenities = $(this).attr('amenities').split(',');
			let status;
			let statusClass;

			const today = new Date()
			fromDate = new Date(fromDate);
			toDate = new Date(toDate);
			if(today < fromDate) {
				status = "Upcoming";
				statusClass = "";
			} else if (today > toDate) {
				status="Completed";
				statusClass = "hidden";
			}


			$('#modalLayer').toggleClass('hidden');
			$('#modalLayer').append(`
				<div class="roomItem h-96 flex flex-row color-3 w-3/4 rounded-lg">
					<div class="`+image+` rounded-l-lg w-2/5"></div>
					<div class="p-2 w-2/3 flex flex-col">
						<div class="flex flex-row justify-between content-center items-center">
							<h1 class="text-2xl baskerville color-5-text flex flex-row content-center items-center gap-4">`+title+` <span class="text-sm arial color-8 rounded-md px-2 color-black font-bold">`+status+`</span></h1>
							<button id="closeModal" class="color-5-text color-4-text-hover">
								<i class="fa-regular fa-circle-xmark fa-xl"></i>
							</button>
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
						<div class="flex flex-col p-2 mt-4 ml-4">
							<p>Check-In</p>
							`+fromDate+`
							<p>Check-Out</p>
							`+toDate+`
						</div>
						<div class="flex flex-col flex-grow"></div>
						<div class="flex flex-row justify-between content-center items-center">
							<p class="text-2xl font-bold color-4-text">$`+overallPrice+` <span class="text-sm">at $`+price+`/night</span></p>
							<button id="cancelRoom" class="`+statusClass+` font-bold color-6 color-black rounded-md px-2">Cancel</button>
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

			$('#cancelRoom').click(function(e) {
				e.preventDefault();
				try {
					let finalData = "reservation=" + reservationid
					swal({
						title: "Cancel Reservation Confirmation",
						text: "Are you sure you want to cancel this Reservation for " + title + " for $" + overallPrice + " From: " + fromDate + " To: " + toDate,
						icon: "info",
						buttons: ['Back', 'Confirm Cancellation']
					}).then((result) => {
						if(result) {
							$.ajax({
								type: 'post',
								url: 'MyAccount?cancelRes',
								data: finalData
							}).then((result) => {
								if(result.success) {
									swal({
										title: "Success",
										text: result.msg,
										icon: "success",
									}).then(() => {
										$('#modalLayer').toggleClass('hidden').empty();
										window.location.reload();
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