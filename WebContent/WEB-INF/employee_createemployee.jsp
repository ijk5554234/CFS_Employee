<jsp:include page="employee_toptemplate.jsp" />
<div id="body" class="container">
	<div class="row">
		<h3 class="form-signin-heading">Create Employee Account</h3>
		<br />
		<h4 style="color: #CCFF66">${msg}</h4>
		<jsp:include page="error-list.jsp" />
		<div id="create_employee_table">
			<form method="POST" action="employee_createemployee.do">
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-user"></span> <input
							type="text" name="userName" class="form-control flat input-hg"
							placeholder="Employee User Name" aria-describedby="basic-addon1"
							required="">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-user"></span><input
							type="text" name="firstName" class="form-control flat input-hg"
							placeholder="Employee First Name" aria-describedby="basic-addon1"
							required="">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-user"></span> <input
							type="text" name="lastName" class="form-control flat input-hg"
							placeholder="Employee Last Name" aria-describedby="basic-addon1"
							required="">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-lock"></span> <input
							type="password" name="password" class="form-control flat input-hg"
							placeholder="Password" aria-describedby="basic-addon1" required="">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<input type="submit" class="btn btn-primary btn-hg btn-block flat"
						name="action" value="Create Employee" />
				</div>
			</form>
		</div>
	</div>
</div>
<br/>


<jsp:include page="employee_bottomtemplate.jsp" />