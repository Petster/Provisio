<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn.email != null}">
	<c:redirect url="/myaccount.jsp"/>
</c:if>
<t:Layout>
	<t:CenteredSection>
		<c:import url="/Lookup" />
	<div id="lookup" class="p-2 flex-grow flex flex-col">
		<h1 class="text-2xl text-center color-4-text font-bold p-8">Look Up a Reservation</h1>
		<div class="flex flex-col content-center items-center justify-center color-3 p-8 rounded-lg">
			<form id="lookupForm" class="flex flex-col flex-grow w-5/6 gap-4">
				<div id="resnumField" class="flex flex-col flex-grow">
					<label for="resnum">Reservation Number</label>
					<input class="w-full rounded-md text-md p-2" name="resnum" type="text" id="resnum"
						   placeholder="Enter your reservation number"/>
				</div>
				<div class="flex flex-col flex-grow">
					<label for="email">Email</label>
					<input class="w-full rounded-md text-md p-2" name="email" type="text" id="email"
						   placeholder="Enter your email"/>
				</div>
				<div class="flex flex-col-reverse md:flex-row gap-4 text-center justify-end flex-grow">
					<button id="submitLookup"
							class="color-2 color-5-hover color-5-text color-2-text-hover py-2 px-20 rounded-md font-bold">
						Search
					</button>
				</div>
			</form>
		</div>
	</div>
		<div id="data" class="hidden p-2 flex-grow flex flex-col">
			<h1 class="text-2xl text-center color-4-text font-bold p-8">Look Up a Reservation</h1>
			<div class="flex flex-col content-center items-center justify-center color-3 p-8 rounded-lg gap-y-4">
				<div id="lookupData" class="flex flex-row flex-grow w-full mx-auto content-center items-center justify-center">

				</div>
				<button id="resetLookup"
						class="color-2 color-5-hover color-5-text color-2-text-hover py-2 px-20 rounded-md font-bold">
					Reset
				</button>
			</div>
		</div>
		<script>
			document.getElementById('resetLookup').addEventListener('click', function() {
				document.getElementById('lookup').classList.toggle('hidden');
				document.getElementById('data').classList.toggle('hidden');
			})

			let resField = document.getElementById('resnum');
			let emailField = document.getElementById('email');

			$('#submitLookup').click(function(e) {
				e.preventDefault();
				if(resField.value == '' || emailField.value == '') {
					swal({
						title: "Error",
						text: 'Please fill all fields!',
						icon: "error",
					});
				} else {

					$.ajax({
						type: 'post',
						url: 'Lookup',
						data: $('#lookupForm').serialize()
					}).then((result) => {
						if(result.success) {
							document.getElementById('lookup').classList.toggle('hidden');
							document.getElementById('data').classList.toggle('hidden');
							let reservation = JSON.parse(result.reservation);
							let location = JSON.parse(result.location);
							let room = JSON.parse(result.room);
							$('#lookupData').empty();
							$('#lookupData').append(`
						<div class="roomItem sm:h-96 flex flex-row flex-grow bg-white sm:w-3/4 rounded-lg">
							<div class="`+room.image+` rounded-l-lg w-2/5"></div>
							<div class="p-2 w-2/3 flex flex-col">
								<div class="flex flex-row justify-between content-center items-center">
									<h1 class="text-2xl baskerville color-5-text flex flex-row content-center items-center gap-4">`+room.title+` <span class="text-sm arial color-8 rounded-md px-2 color-black font-bold">`+status+`</span></h1>
								</div>
								<h2 class="text-lg baskerville color-5-text mt-4">Amenities</h2>
								<div class="border-b-2 border-black"></div>
								<div id="amenities" class="flex flex-row flex-wrap gap-3 mt-1 py-3">
								</div>
								<h2 class="text-lg baskerville color-5-text">Room Highlights</h2>
								<div class="border-b-2 border-black"></div>
								<div class="flex flex-row justify-between p-2 m-2 h-24 overflow-auto tinyscroll">
									`+room.room_highlights+`
								</div>
								<h2 class="text-lg baskerville color-5-text">Reservation Details</h2>
								<div class="border-b-2 border-black"></div>
								<div class="flex flex-col p-2 m-2 overflow-auto tinyscroll">
									<p class="text-md font-bold">Reservation Number: `+reservation._id+`</p>
									<p class="text-md font-bold">`+reservation._guests+` Guests</p>
									<p class="text-md font-bold">`+room.loyalty_points+` Loyalty Points Earned</p>
									<p class="text-md font-bold">`+location.title+`</p>
									<p>&ensp;`+location.address+`</p>
									<p class="text-md font-bold">Check-In</p>
									<p>&ensp;`+reservation._fromDate+`</p>
									<p class="text-md font-bold">Check-Out</p>
									<p>&ensp;`+reservation._toDate+`</p>
								</div>
								<div class="flex flex-col flex-grow"></div>
								<div class="flex flex-row justify-between content-center items-center">
									<p class="text-2xl font-bold color-4-text">$`+reservation._price+` <span class="text-sm">at $`+room.price+`/night</span></p>

								</div>
							</div>
						</div>
						`)

							if(room.breakfast) {
								$('#amenities').append(`<i class="fa fa-coffee fa-xl color-5-text"></i>`)
							}
							if(room.wifi) {
								$('#amenities').append(`<i class="fa fa-wifi fa-xl color-5-text"></i>`)
							}
							if(room.fitness) {
								$('#amenities').append(`<i class="fa fa-dumbbell fa-xl color-5-text"></i>`)
							}
							if(room.store) {
								$('#amenities').append(`<i class="fa fa-store fa-xl color-5-text"></i>`)
							}
							if(room.nosmoke) {
								$('#amenities').append(`<i class="fa fa-ban-smoking fa-xl color-5-text"></i>`)
							}
							if(room.mobile) {
								$('#amenities').append(`<i class="fa fa-mobile fa-xl color-5-text"></i>`)
							}
						} else {
							swal({
								title: "Error",
								text: result.msg,
								icon: "error",
							});
						}
					})
				}
			});
		</script>
	</t:CenteredSection>
</t:Layout>