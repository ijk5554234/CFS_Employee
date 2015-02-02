<jsp:include page="employee_toptemplate.jsp" />
<div id="body" class="container">
	<div class="row">
		<h3 class="form-signin-heading">Change Your Password</h3>
		<br />
		<h4 style="color: #CCFF66">${msg}</h4>
		<jsp:include page="error-list.jsp" />
		<div id="chpw_form">
			<form method="POST" action="employee_changepsw.do">
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-lock"></span><input
							type="password" name="newPassword"
							class="form-control flat input-hg" placeholder="New Password"
							aria-describedby="basic-addon1">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-lock"></span><input
							type="password" name="confirmPassword" class="form-control flat input-hg"
							placeholder="Confirm Password" aria-describedby="basic-addon1">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<input type="submit" class="btn btn-primary btn-hg btn-block flat"
						name="action" value="Change Password" />
				</div>
			</form>
		</div>
	</div>
</div>


<br/>
<br/>

<jsp:include page="employee_bottomtemplate.jsp" />
