<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
function search(form) {
	var selectedIndex = form.categoriesSelector.selectedIndex;
	var options = form.categoriesSelector.options;
	var category = options[selectedIndex].text;
	var name = form.name.value;
	
	$.ajax ({
        type: "POST",
        url: "search",
        dataType: "json",
        data: "name=" + name + "&category=" + category,
        success: function(msg) {
        	alert(msg);
        }
    });
	
	event.preventDefault();
}
</script>

<form class="navbar-form navbar-left" role="search" method="post"
	action="search">
	<div class="form-group navbar-left">
		<input type="text" name="name" class="form-control" placeholder="name">
	</div>

	<div class="form-group">
		<div class="col-xs-5 selectContainer">
			<select name="category" class="form-control">
				<option selected="selected" value="All">all categories</option>
				<c:forEach var="listValue" items="${categories}">
					<option>${listValue}</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<button type="submit" class="btn btn-default navbar-right">search</button>

</form>