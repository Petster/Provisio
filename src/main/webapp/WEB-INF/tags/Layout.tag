<%@tag description="Page Layout" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://unpkg.com/tailwindcss@^1.5/dist/base.min.css">
<link rel="stylesheet" href="https://unpkg.com/tailwindcss@^1.5/dist/components.min.css">
<link rel="stylesheet" href="https://unpkg.com/@tailwindcss/typography@0.2.x/dist/typography.min.css">
<link rel="stylesheet" href="https://unpkg.com/tailwindcss@^1.5/dist/utilities.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>Provisio</title>
<link rel="stylesheet" href="css/global.css?ver=1">
<base href="" />
</head>
	<body>
		<main class="mx-auto flex flex-col min-h-screen sm:break-words md:break-normal">
			<t:Header></t:Header>
			<section class="flex flex-grow p-4">
				<div class="flex flex-col flex-grow text-center mx-auto w-full">
					<div class="text-white flex flex-col lg:flex-row flex-grow lg:content-center lg:justify-center backdrop-filter backdrop-blur-md bg-black bg-opacity-40 p-4 rounded-lg">
						<jsp:doBody />
					</div>
				</div>
			</section>
			<t:Footer></t:Footer>
		</main>
	</body>
</html>