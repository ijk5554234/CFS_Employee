$("select").select2({
	dropdownCssClass : 'dropdown-inverse'
});
var pie = $("#pie").html();
var bar = $("#bar").html();
var pies = pie.substring(0, pie.length).split(',');
var bars = bar.substring(0, bar.length).split(',');
var data1 = [ {
	value : parseInt(pies[0]),
	color : "#F7464A",
	highlight : "#FF5A5E",
	label : "Buy"
}, {
	value : parseInt(pies[1]),
	color : "#46BFBD",
	highlight : "#5AD3D1",
	label : "Sell"
}, {
	value : parseInt(pies[2]),
	color : "#FDB45C",
	highlight : "#FFC870",
	label : "requestCheck"
}, {
	value : parseInt(pies[3]),
	color : "#FDB45C",
	highlight : "#FFC870",
	label : "depositCheck"
} ]
var data2 = {
	labels : [ "Buy Sum", "Check Sum", "Deposit Sum" ],
	datasets : [ {
		label : "My First dataset",
		fillColor : "rgba(220,220,220,0.5)",
		strokeColor : "rgba(220,220,220,0.8)",
		highlightFill : "rgba(220,220,220,0.75)",
		highlightStroke : "rgba(220,220,220,1)",
		data : bars
	} ]
}

var ctx1 = $("#myPieChart").get(0).getContext("2d");
var ctx2 = $("#myBarChart").get(0).getContext("2d");
new Chart(ctx1).Pie(data1);
new Chart(ctx2).Bar(data2);