<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.webstoremvc.database.model.Commodity"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<jsp:include page="../partialJsps/includes.jsp" />

<script>

$(function() {
	$('.numberinput').keyup(function(){
		var price = $(this).parent().children(".priceForOne").html();
		var quantity = $(this).val();
		$(this).parent().children(".price").children(".priceNumber").html(price * quantity);
	});

});
</script>

</head>
<body>


	<jsp:include page="../partialJsps/header.jsp" />

	<div class="container">
		<div class="row">
			<c:forEach var="commodity" items="${merchandises}">
				<div class="col-md-3">
					<img src="/webstoremvc/images/${commodity.key.name}" alt="no img" style="width: 150px; height: 250px; float: left;">
					<div>${commodity.key.name}</div>
					<div class="priceForOne">${commodity.key.price}</div>
					<input type='number' size='3' class='numberinput' name='mynumber' value='${commodity.value}' />
					<div class='price'><span class="priceNumber">${commodity.key.price * commodity.value}</span> grn total</div>
				</div>
			</c:forEach>
		</div>
	</div>



</body>
</html>