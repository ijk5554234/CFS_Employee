<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee_toptemplate.jsp" />
<br/><br/>
<div id="body" class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="form_area panel">
			<h3 class="form-signin-heading">Create Customer
                    Account</h3>
                  <h4 style="color: #CCFF66">${msg}</h4>
					<jsp:include page="error.jsp" />

		 		<div id="create_customer_table">

					<form method="POST" action="employee_createcustomer.do">
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<input type="email" name="email" class="form-control flat"
									placeholder="*Email" aria-describedby="basic-addon1"> <span
									class="form-control-feedback fui-mail"></span>
							</div>
						</div>

						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-user"></span><input
									type="text" name="firstName" class="form-control flat"
									placeholder="*First Name" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-user"></span> <input
									type="text" name="lastName" class="form-control flat"
									placeholder="*Last Name" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-home"></span> <input
									type="text" name="addrLine1" class="form-control flat"
									placeholder="Address Line1" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-home"></span> <input
									type="text" name="addrLine2" class="form-control flat"
									placeholder="Address Line2" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-location"></span> <input
									type="text" name="city" class="form-control flat" placeholder="City"
									aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-location"></span> <input
									type="text" name="state" class="form-control flat"
									placeholder="State" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-list-numbered"></span> <input
									type="text" name="zip" class="form-control flat" placeholder="Zip"
									aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<div class="form-group has-feedback">
								<span class="form-control-feedback fui-credit-card"></span> <input
									type="text" name="cash" class="form-control flat" placeholder="*Cash Balance 0"
									aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="input-group col-md-10 col-md-offset-1">
							<input type="submit" class="btn btn-primary btn-block flat"
								name="action" value="Create Customer" />
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</div>



<jsp:include page="employee_bottomtemplate.jsp" />