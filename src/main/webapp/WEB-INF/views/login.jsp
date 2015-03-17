<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../partialJsps/includes.jsp" />
<script type="text/javascript">
	function check(form) {
		$.ajax({
			type : "POST",
			url : "loginPost",
			dataType : "text",
			data : "name=" + form.name.value + "&pass=" + form.pass.value,
			success : function(msg) {
				if (msg == "yes")
					window.location = "http://localhost:8080/webstoremvc";
			}
		});

		event.preventDefault();
	}
</script>
</head>

<body>
<div class="container">
	<jsp:include page="../partialJsps/header.jsp" />
	
	<form id="contactForm" method="post" onsubmit="check(this);">
		<div class="row">
			<div class="col-xs-4">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">username</span>
					 <input name="name" type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" value="user1">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4">
				<div class="input-group">
					<span value="user1" class="input-group-addon" id="basic-addon1">password</span> 
					<input name="pass" value="pass1" type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2">
				<button type="submit" class="btn btn-default">login</button>
			</div>
		</div>

	</form>
</div>
</body>

</html>