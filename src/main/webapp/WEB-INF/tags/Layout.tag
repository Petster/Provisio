<%@tag description="Page Layout" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<t:Head></t:Head>
<body>
<main class="mx-auto flex flex-col min-h-screen sm:break-words md:break-normal">
	<t:Header></t:Header>
	<jsp:doBody />
	<t:Footer></t:Footer>
</main>
<div id="modalLayer" class="hidden flex flex-col content-center items-center justify-center">

</div>
</body>
<script src="js/global.js"></script>
<script src="js/ajax.js"></script>
</html>