<%@page import="java.util.Set" %>
<%@page import="com.webstoremvc.database.model.Category" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
.menuOption {
padding-left: 0 !important;
}
</style>

<!-- sidebar -->
        <div class="col-xs-1 sidebar-offcanvas navbar-default" id="sidebar" role="navigation">
            <ul class="nav navbar-nav">
               <c:forEach var="listValue" items="${categories}">
					<li><a class="menuOption" href="/webstoremvc/category/${listValue}">${listValue}</a></li>
				</c:forEach>              
            </ul>
        </div>     
