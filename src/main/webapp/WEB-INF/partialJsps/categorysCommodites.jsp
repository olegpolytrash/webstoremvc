<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.List"%>
<%@page import="com.webstoremvc.database.model.Commodity"%>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript">
        function add(desc)
            {            	
        	alert(desc.name);
                $.ajax({
                    type: "GET",
                    url: "addCart",
                    dataType: "text/html",
                    data: "input=" + desc
                });
            }
    </script>

<c:forEach var="commodity" items="${commodities}">
	<div class="commodityName col-xs-4" name="${commodity.id}">
		<div style="width: 150px; height: 250px; float: left;">
			<img style="max-width: 140px; position: relative; top: 5px;" src="/webstoremvc/images/${commodity.name}" alt="image absent" />
		</div>
		<div>${commodity.name}</div>
		<div class='price'>${commodity.price}</div>
		<button class="add btn btn-default" name="${commodity.id}" onclick="add(${commodity.id});" type="button">buy</button>
	</div>
</c:forEach>
