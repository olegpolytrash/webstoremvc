<%@page import="com.webstoremvc.constant.AttributeConstants"%>

<%
	Integer shoppingCartValue = (Integer) request.getAttribute(AttributeConstants.ATTRIBUTE_SHOPPING_CART.toString());

	boolean logedIn = false;
	if (request.getAttribute(AttributeConstants.ATTRIBUTE_LOGIN.toString()) == AttributeConstants.LOGIN_TRUE)
		logedIn = true;
%>

<nav class="navbar navbar-default navbar-static-top" role="navigation">
	<div class="container-fluid">
	
		<a class="navbar-brand navbar-static-left" href="/webstoremvc">WebStoreMvc</a>

		<jsp:include page="search.jsp" />

		<div class="navbar-header navbar-right" id="bs-WSM-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/webstoremvc/cart">cart: <span id="shoppingCartValue"><%=shoppingCartValue%></span> goods</a></li>
				
				<% if (!logedIn) { %>
				<li><a href="http://localhost:8080/webstoremvc/login">login</a></li>
				<li><a href="http://localhost:8080/webstoremvc/register">register</a></li>
				<% } else { %>
				<li><a href="http://localhost:8080/webstoremvc/personalInfo">account</a></li>
				<li><a href="http://localhost:8080/webstoremvc/logOut">log out</a></li>
				<% } %>
			</ul>
		</div>
		</div>
	
</nav>

<br />