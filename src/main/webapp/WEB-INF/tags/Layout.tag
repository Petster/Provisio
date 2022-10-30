<%@tag description="Page Layout" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
			<section class="flex flex-grow md:justify-center md:w-4/5 md:mx-auto bg-gray-300">
					<jsp:doBody />
			</section>
			<t:Footer></t:Footer>
		</main>
	</body>
	<script src="js/global.js"></script>
</html>