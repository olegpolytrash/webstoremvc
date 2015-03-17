<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.List" %>
<%@page import="com.webstoremvc.database.model.Commodity" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../partialJsps/includes.jsp" />
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