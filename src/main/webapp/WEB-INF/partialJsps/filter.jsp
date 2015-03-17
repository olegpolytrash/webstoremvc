
<script type="text/javascript"
	src="/webstoremvc/resources/javascript/slider.js"></script>

<script type="text/javascript">
	function filterClick() {
		event.preventDefault();

		minValueText;
		maxValueText;

		var ids = "";
		$(".commodityName").each(function(index, element) {
			var price = $(this).children(".price").html();
			if (price < $("#minValueSlider").val() || price > $("#maxValueSlider").val()) {
				$(this).hide();
			} else {
				$(this).show();
			}
		});
	}
</script>

</head>
<body>

	<%
		Integer filterMinPrice = (Integer) request.getAttribute("minPrice");

		if (request.getAttribute("filterMinPrice") != null) {
			filterMinPrice = (Integer) request.getAttribute("filterMinPrice");
		}

		Integer filterMaxPrice = (Integer) request.getAttribute("maxPrice");


		if (request.getAttribute("filterMaxPrice") != null) {
			filterMaxPrice = (Integer) request.getAttribute("filterMaxPrice");
		}
	%>

	<form method="post" action="/filter" onsubmit="filterClick();">



		<div id="slider" class="container">
			<div class="row">
			
			<div class="input-group col-xs-1"
				style="float: left; min-width: 120px;">
				<span class="input-group-addon">min</span> 
				<input id="minPrice" onkeypress="onMinValueChanged(this.value);" name="minPrice" 
				value="<%=filterMinPrice%>" type="text" class="form-control">
			</div>

			<div class="col-xs-4">
				<input type="range" id="minValueSlider" value="<%=filterMinPrice %>" min="${minPrice}" max="${maxPrice}" oninput="showMinValue(this.value)"> 
				<input type="range" id="maxValueSlider" value="<%=filterMaxPrice %>" min="${minPrice}" max="${maxPrice}" oninput="showMaxValue(this.value)">
			</div>

			<div class="input-group col-xs-1" style="min-width: 120px;">
				<span class="input-group-addon" id="sizing-addon1">max</span> 
				<input id="maxPrice" name="maxPrice" value="<%=filterMaxPrice%>" type="text" class="form-control" aria-describedby="sizing-addon1">
			</div>
		</div>
		<div class="row">
			<input class="btn btn-default" type="submit" value="filter" />
		</div>
		</div>
	</form>