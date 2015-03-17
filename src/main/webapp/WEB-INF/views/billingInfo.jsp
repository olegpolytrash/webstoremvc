<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../partialJsps/header.jsp" />
<jsp:include page="../partialJsps/accountHeader.jsp" />

<form>
<input type="text" placeholder="card number" /> <br/>
Expiration date <input type="text" placeholder="month" /> / <input type="text" placeholder="year" /> 
<br/>
<input type="text" placeholder="cvc" /> <br/>

<input type="submit" />

</form>

</body>
</html>