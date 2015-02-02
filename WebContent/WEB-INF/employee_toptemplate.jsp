<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Carnegie Financial Service</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Loading Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Loading Flat UI -->
<link href="css/flat-ui.css" rel="stylesheet">
<link href="css/cfs.css" rel="stylesheet">

<link rel="shortcut icon" href="img/2.png">

</head>
<body>
	<div id="header">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse-01">
					<span class="sr-only">Toggle navigation</span>
				</button>
				<a class="navbar-brand" href="employee_welcome.do">CFS</a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse-01">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" role="button"
						aria-expanded="false">Manage Customer Account <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employee_createcustomer.do">Create Customer
									Account</a></li>
							<li><a href="customer_view_list.do">View Customer List</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" role="button"
						aria-expanded="false">Manage Employee Account <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="employee_createemployee.do">Create Employee
									Account</a></li>
							<li><a href="employee_changepsw.do">Reset Your Password</a></li>
						</ul></li>
					<li><a href="employee_createfund.do">Create Funds</a></li>
					<li><a href="employee_transitionday.do">Transition Day</a></li>
					<li><a href="employee_logout.do">Logout</a></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</nav>
		<!-- /navbar -->
	</div>

	<noscript>
		<div>
			<button type="button">
				<span>Toggle navigation</span>
			</button>
			<a class="navbar-brand" href="employee_welcome.do">CFS</a>
		</div>
		<br/>
		<br/>
		<br/>
		<div>
			<ul>
				<li><a href="employee_createcustomer.do">Create Customer</a></li>
				<li><a href="customer_view_list.do">View Customer</a></li>
				<li><a href="employee_createemployee.do">Create Employee</a></li>
				<li><a href="employee_changepsw.do">Reset Password</a></li>
				<li><a href="employee_createfund.do">Create Funds</a></li>
				<li><a href="employee_transitionday.do">Transition Day</a></li>
				<li><a href="employee_logout.do">Logout</a></li>
			</ul>
		</div>
	</noscript>