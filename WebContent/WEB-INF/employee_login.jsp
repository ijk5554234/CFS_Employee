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
	<div class="container">
		<br /> <br />
		<h1>Welcome Employee!</h1>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<form class="form-signin" method="POST">

					<h3 class="form-signin-heading">Please login</h3>
					<jsp:include page="error.jsp" />
					<div class="form-group has-feedback">
						<input name="userName" type="text" class="form-control flat input-hg"
							placeholder="UserName" autofocus> <span
							class="form-control-feedback fui-mail"></span>
					</div>
					<div class="form-group has-feedback">
						<input name="password" type="password" class="form-control flat input-hg"
							placeholder="Password"> <span
							class="form-control-feedback fui-lock"></span> <input
							name="action" type="hidden" value="Login">
					</div>
					<div style="text-align: center">
						<a href="http://54.173.57.219:8080/home%20page/"> Back to Home</a>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="employee_bottomtemplate.jsp" />