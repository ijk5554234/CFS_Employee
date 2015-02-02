<jsp:include page="employee_toptemplate.jsp" />
<div id="body" class="container">
	<div class="row">
		<h3 class="form-signin-heading">Create Fund</h3>
		<br />
		<h4 style="color: #CCFF66">${msg}</h4>
 		<jsp:include page="error-list.jsp" />
			<div id="chpw_form">
			<form method="POST" action="employee_createfund.do">
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-bookmark"></span>  <input
							type="text" name="fundName" class="form-control flat input-hg"
							placeholder="Fund Name" aria-describedby="basic-addon1">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<div class="form-group has-feedback">
						<span class="form-control-feedback fui-tag"></span> <input
							type="text" name="symbol" class="form-control flat input-hg"
							placeholder="Ticker" aria-describedby="basic-addon1"
							maxlength="5">
					</div>
				</div>
				<div class="input-group col-md-8 col-md-offset-2">
					<input type="submit" class="btn btn-primary btn-block flat btn-hg"
						name="action" value="Create Fund" />
				</div>
			</form>
		</div>
	</div>
</div>
<br/>
<br/>
<br/>



<jsp:include page="employee_bottomtemplate.jsp" />