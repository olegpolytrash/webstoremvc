<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.webstoremvc.constant.AttributeConstants"%>
<!doctype html>
<html lang="en">

<head>
<jsp:include page="../partialJsps/includes.jsp" />

<script>

	/*
	(/^
	(?=.*\d)                //should contain at least one digit
	(?=.*[a-z])             //should contain at least one lower case
	(?=.*[A-Z])             //should contain at least one upper case
	[a-zA-Z0-9]{8,}         //should contain at least 8 from the mentioned characters
	$/)
	 */

	function checkForm(form) {

		var pattern = new RegExp(
				/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/);

		if (!pattern.test(form.pass.value)) {
			alert("password must be at least 8 didgits long and contain at least: one digit, a lower and an upper case letter and no whitespaces");
			event.preventDefault();
			return;
		}

		if (form.pass.value != form.pass2.value) {
			alert("passwords are not the same");
			event.preventDefault();
		}
	}
</script>
</head>

<body>

<jsp:include page="../partialJsps/header.jsp" />

	<form id="contactForm" method="post" action="registerPost" onsubmit="checkForm(this);">
		<input type="email" name="email" required placeholder="email address" value="someemail@gmail.com" /><br />
		<input type="text" name="name" required placeholder="name" value="user"> <br /> 
		<input type="password" name="pass" required placeholder="password" value="12345678Aa"> <br /> 
		<input type="password" name="pass2" required placeholder="password again" value="12345678Aa"> <br />
		
		<p>
			<input class="submit" type="submit" value="register" />
		</p>
	</form>
	
	<%
			Object successAttr = request.getAttribute(AttributeConstants.ATTRIBUTE_REGISTER.toString());
			String message = "";
			if (successAttr == AttributeConstants.ATTRIBUTE_REGISTER_SUCCESS) {
				message = "register successful";
			} else if (successAttr == AttributeConstants.ATTRIBUTE_REGISTER_FAILURE) {
				message = "this username is already in use";
			}
		%>
		
	<div><%=message %></div>
	
</body>

</html>