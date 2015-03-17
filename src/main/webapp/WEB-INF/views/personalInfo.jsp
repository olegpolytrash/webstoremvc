<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../partialJsps/includes.jsp" />
</head>
<body>
<jsp:include page="../partialJsps/header.jsp" />
<jsp:include page="../partialJsps/accountHeader.jsp" />

<form>
<input type="text" placeholder="Email" /> <br/>
<input type="text" placeholder="Country" /> <br/>
<input type="text" placeholder="State/province" /> <br/>
<input type="text" placeholder="City" /> <br/>
<input type="text" placeholder="Street" /> <br/>

<input type="submit" />

</form>

</body>
</html>