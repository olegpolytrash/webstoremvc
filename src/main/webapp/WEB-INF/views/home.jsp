<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>

<jsp:include page="../partialJsps/includes.jsp" />

<style>

</style>

</head>
<body>
<div class="container">
	<jsp:include page="../partialJsps/header.jsp" />
	<jsp:include page="../partialJsps/sideBar.jsp" />

	<div class="col-xs-9">

		<jsp:include page="../partialJsps/filter.jsp" />
		<br />
		<jsp:include page="../partialJsps/categorysCommodites.jsp" />

	</div>
</div>
</body>
</html>
