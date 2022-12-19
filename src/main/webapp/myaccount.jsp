<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn == null}">
	<c:redirect url="/login.jsp?next=myaccount"/>
</c:if>
<c:if test="${sessionScope.LoggedIn.email == null}">
	<c:redirect url="/index.jsp"/>
</c:if>
<t:Layout>
	<t:CenteredSection>
	<c:import url="/MyAccount" />
	<div class="flex flex-col-reverse md:flex-row w-full gap-4 p-4">
		<div id="roomsList" class="flex flex-col flex-grow color-3 p-2 md:w-2/3 rounded-lg overflow-y-auto newsbox" style="min-height: 800px">
			<h1 class="text-start text-2xl font-bold  color-4-text baskerville">Reservations</h1>
			<div class="border-b-2 color-4-border w-2/4"></div>
			<div id="roomWrapper" class="grid gap-4 grid-cols-1 xl:grid-cols-2 gap-3 mt-2">
				<c:forEach items="${myReservations}" var="i" >
					<c:forEach items="${allRooms}" var="k">
						<c:forEach items="${allLocations}" var="n">
							<c:if test="${i.roomType == k.ID}">
								<c:if test="${i.location == n.ID}">
									<div earned="${k.loyaltyPoints}" guests="${i.guests}" location="${n.title}" address="${n.address}" reservationid="${i.id}" reservedate="${i.reserveDate}" fromDate="${i.fromDate}" toDate="${i.toDate}" roomType="${k.ID}" overallPrice="${i.price}" price="${k.price}" title="${k.title}" image="${k.image}" desc="${k.highlights}" amenities="${k.breakfast},${k.wifi},${k.fitness},${k.store},${k.noSmoke},${k.mobile}" class="roomItem flex flex-row color-2 hover:cursor-pointer w-full rounded-lg">
										<div class="${k.image} rounded-l-lg w-2/5"></div>
										<div class="p-2 w-2/3 flex flex-col">
											<h1 class="text-2xl baskerville color-5-text"><c:out value="${k.title}"></c:out></h1>
											<h2 class="text-lg baskerville color-5-text mt-4">Amenities</h2>
											<div class="border-b-2 border-black"></div>
											<div class="flex flex-row flex-wrap gap-y-8 gap-3 mt-1 py-3">
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
							</c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
		<div class="flex flex-col md:w-1/3 gap-4">
			<div class="flex flex-col color-3 p-4 rounded-lg">
				<h1 class="text-start text-2xl font-bold  color-4-text baskerville">Loyalty Rewards</h1>
				<div class="border-b-2 color-4-border w-3/5"></div>
				<div class="flex flex-col xl:flex-row p-2 gap-3 justify-center items-center content-center sm:justify flex-wrap sm:flex-nowrap">
					<div class="flex flex-col">
						<div class="pie" data-pie='{ "lineargradient": ["#667761","#667761"], "percent": <c:out value='${sessionScope.LoggedIn.loyaltyPoints}' />, "unit": " Points", "fontSize": "0.7rem", "colorSlice": "#DCEED1", "colorCircle": "#DCEED1", "stroke": 5, "rotation": 0 }'></div>
					</div>
					<div class="flex flex-col content-center items-center gap-y-10">
						<h1 id="loyaltyEncouragement" class="text-lg font-bold text-center"></h1>
						<i id="reward" class="animate__animated animate__slow animate__delay-2s animate__repeat-3 fa-solid fa-gift fa-4x"></i>
					</div>
				</div>
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
		let lp = <c:out value="${sessionScope.LoggedIn.loyaltyPoints}" />;
		$(document).ready(function() {
			let loyaltyEncouragement;
			const circle = new CircularProgressBar("pie");
			circle.initial();

			switch(true) {
				case (lp <= 0):
					loyaltyEncouragement = "Create a reservation to start earning points!"
					break;
				case (lp <= 25):
					loyaltyEncouragement = "Starting strong!"
					break;
				case (lp <= 50):
					loyaltyEncouragement = "Almost there!"
					break;
				case (lp <= 75):
					loyaltyEncouragement = "So close you can almost touch it!"
					break;
				case (lp <= 99):
					loyaltyEncouragement = "The finish line is up ahead!"
					break;
				case (lp >= 100):
					loyaltyEncouragement = "You are ready for a reward!"
					break;
			}

			if(lp >= 100) {
				$('.pie-unit-1').html('100+ Points')
			}

			document.getElementById('loyaltyEncouragement').innerHTML = loyaltyEncouragement;
		})

		if(lp >= 100) {
			document.getElementById('reward').classList.add('animate__wobble');
			document.getElementById('reward').classList.add('cursor-pointer');
			document.getElementById('reward').addEventListener('click', function() {
				swal({
					title: "Congratulations!",
					text: 'You have earned a coupon for 50% off your next reservation!',
					icon: "success",
				})
			})
		}

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
			let location = $(this).attr('location');
			let address = $(this).attr('address');
			let guests = $(this).attr('guests');
			let earned = $(this).attr('earned');
			let status;
			let statusClass;

			const today = new Date()
			fromDate = new Date(fromDate);
			toDate = new Date(toDate);
			if(today > fromDate) {
				status = "Ongoing";
				statusClass = "hidden";
			} else if(today <= fromDate) {
				status = "Upcoming";
				statusClass = "";
			} else if (today > toDate) {
				status="Completed";
				statusClass = "hidden";
			}


			$('#modalLayer').toggleClass('hidden');
			$('#modalLayer').append(`
				<div class="roomItem sm:h-96 flex flex-row color-3 sm:w-3/4 rounded-lg">
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
						<div id="amenities" class="flex flex-row flex-wrap gap-3 mt-1 py-3">
						</div>
						<h2 class="text-lg baskerville color-5-text">Room Highlights</h2>
						<div class="border-b-2 border-black"></div>
						<div class="flex flex-row justify-between p-2 m-2 h-24 overflow-auto tinyscroll">
							`+desc+`
						</div>
						<h2 class="text-lg baskerville color-5-text">Reservation Details</h2>
						<div class="border-b-2 border-black"></div>
						<div class="flex flex-col p-2 m-2 overflow-auto tinyscroll">
							<p class="text-md font-bold">Reservation Number: `+reservationid+`</p>
							<p class="text-md font-bold">`+guests+` Guests</p>
							<p class="text-md font-bold">`+earned+` Loyalty Points Earned</p>
							<p class="text-md font-bold">`+location+`</p>
							<p>&ensp;`+address+`</p>
							<p class="text-md font-bold">Check-In</p>
							<p>&ensp;`+fromDate+`</p>
							<p class="text-md font-bold">Check-Out</p>
							<p>&ensp;`+toDate+`</p>
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
	</t:CenteredSection>
</t:Layout>