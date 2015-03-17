/**
 * 
 */

var minValue = 0;

var minValueText;
var maxValueText;
var maxValueSlider;
var minValueSlider;

$(function() {
	maxValueSlider = $("#maxValueSlider");
	minValueSlider = $("#minValueSlider");
	minValueText = $("#minPrice");
	maxValueText = $("#maxPrice");
});

function showMinValue(newValue) {
	if (newValue >= maxValueSlider.value) {
		minValueSlider.value = maxValueSlider.value - 1;
		return;
	}

	minValue = newValue;
	minValueText.val(newValue);
}

function showMaxValue(newValue) {
	if (newValue <= minValue) {
		maxValueSlider.value = minValue;
		return;
	}

	maxValueText.val(newValue);
}

function onMinValueChanged(newValue) {
	if (newValue < minValueSlider.attr('min')) {
		newValue = minValueSlider.attr('min');
	}
	
	minValueSlider.val(newValue);
}