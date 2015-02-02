
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee_toptemplate.jsp" />
<br />
<br />
<div id="body" class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="form_area panel">
				<h3 class="form-signin-heading">Change Customer Password</h3>
				<h3 style="color: green">${msg}</h3>
				<jsp:include page="error-list.jsp" />
				<div id="chpw_form">
					<form method="POST" action="employee_setcustomerpsw.do">
					<input value="${customerId}" name="customerId" type="hidden">

						<div class="input-group col-md-8 col-md-offset-2">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-lock"></span> <input
									type="password" name="newPassword"
									class="form-control input-hg flat" placeholder="New Password"
									aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-8 col-md-offset-2">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-lock"></span> <input
									type="password" name="confirmPassword"
									class="form-control input-hg flat"
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
	</div>
</div>




<jsp:include page="employee_bottomtemplate.jsp" />
