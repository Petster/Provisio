<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:Layout>
	<t:NormalSection>
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
		<div class="flex flex-col md:flex-row flex-grow gap-4">
			<div id="follow" class="color-3 rounded flex flex-grow flex-col md:w-3/5 p-4 mx-2 md:mx-0">
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
			<div id="news" class="color-3 rounded flex flex-grow flex-col md:w-2/5 p-4 overflow-y-scroll newsbox mx-2 md:mx-0" style="min-height:400px;max-height:400px">
				<h1 class="text-3xl color-4-text font-bold">Recent News</h1>
				<c:import url="/Index" />
				<c:forEach items="${allNews}" var="i" >
					<div class="flex flex-col">
						<div class="flex flex-row content-center items-center justify-between border-b border-b-black">
							<p class="font-bold text-lg"><c:out value="${i.title}" /></p>
							<p><c:out value="${i.publishDate}" /></p>
						</div>
						<div>
							<img src="${i.image}" alt="Provisio Logo" class="h-64 w-full" />
						</div>
						<div>
							<p><c:out value="${i.description}" /></p>
						</div>
					</div>
					<br/>
				</c:forEach>
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
		
		let follow = document.getElementById('follow').offsetHeight;
		
		window.onload = (event) => {
			calculateHeight();
		}
		
		const calculateHeight = () => {
			let follow = document.getElementById('follow').offsetHeight;
			let news = document.getElementById('news');
			news.style.maxHeight = follow + "px";
		}
		
		window.onresize = calculateHeight;
	</script>
	</t:NormalSection>
</t:Layout>