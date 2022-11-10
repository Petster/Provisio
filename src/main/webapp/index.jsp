<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:Layout>
	<div class="flex-grow flex flex-col gap-4 pb-4">
		<section class="splide" class="h-96" aria-label="Splide Basic HTML Example">
		  <div class="splide__track">
				<ul class="splide__list">
					<li class="splide__slide" data-splide-interval="8000"><div id="slideshowImage1" class="h-96"></div></li>
					<li class="splide__slide" data-splide-interval="8000"><div id="slideshowImage2" class="h-96"></div></li>
					<li class="splide__slide" data-splide-interval="8000"><div id="slideshowImage3" class="h-96"></div></li>
					<li class="splide__slide" data-splide-interval="8000"><div id="slideshowImage4" class="h-96"></div></li>
				</ul>
		  </div>
		</section>
		<div class="flex flex-row flex-grow gap-4">
			<div class="color-3 rounded flex flex-grow flex-col w-3/5 p-4">
				<c:if test="${sessionScope.LoggedIn.email != null}">
					<h1 class="text-3xl color-4-text font-bold">Welcome back, <c:out value="${sessionScope.LoggedIn.firstname}" /></h1>
				</c:if>
				<c:if test="${sessionScope.LoggedIn.email == null}">
					<h1 class="text-3xl color-4-text font-bold">Welcome</h1>
				</c:if>
				<p class="text-lg color-4-text">Provisio offers only the best deals in hotel reservations so you can focus on what matters to you.</p>
				<br/>
				<p class="text-lg color-4-text">Whether its for business conferences or a vacation destination with family and friends or even a new adventure, we curate the best destinations for your next trip.</p>
			</div>
			<div class="color-3 rounded flex flex-grow flex-col w-2/5 p-4 overflow-y-scroll newsbox" style="max-height: 400px;">
				<h1 class="text-3xl color-4-text font-bold">Recent News</h1>
				<div class="flex flex-col">
					<div class="flex flex-row content-center items-center justify-between border-b border-b-black">
						<p class="font-bold text-lg">News Title</p>
						<p>News Date</p>
					</div>
					<div>
						<img src="img/LogoBlackSquareTransparent.png" alt="Provisio Logo" class="w-full" />
					</div>
					<div>
						<p>content here</p>
					</div>
				</div>
				<br/>
				<div class="flex flex-col">
					<div class="flex flex-row content-center items-center justify-between border-b border-b-black">
						<p class="font-bold text-lg">News Title</p>
						<p>News Date</p>
					</div>
					<div>
						<img src="img/LogoBlackSquareTransparent.png" alt="Provisio Logo" class="w-full" />
					</div>
					<div>
						<p>content here</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		document.addEventListener( 'DOMContentLoaded', function() {
			var splide = new Splide( '.splide', {
				classes: {
					arrows: 'splide__arrows color-4 color-4-text',
					arrow : 'splide__arrow color-4 color-4-text',
					page: 'splide__pagination__page color-4 color-4-text'
				},
				  type    : 'loop',
				  perPage : 1,
				  autoplay: true,
				  rewind: true,
				} );

				splide.mount();
		  } );
	</script>
</t:Layout>