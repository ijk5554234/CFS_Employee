<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee_toptemplate.jsp" />

<div id="body" class="container">
	<div class="row">
		<h3 class="form-signin-heading">${title}</h3>
		<h4 style="color: #CCFF66">${msg}</h4>
		<c:if test="${customers != null}">
			<div class="col-md-10 col-md-offset-1">

				<table class="table table-hover">
					<thead>
						<tr>
							<th>Email</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th style="text-align: right">Cash</th>
							<th style="text-align: center">Option</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="customer" items="${customers}">
							<tr>
								<td>${customer.email}</td>
								<td>${customer.firstName}</td>
								<td>${customer.lastName}</td>
								<td style="text-align: right">${customer.cash}</td>
								<td><div class="btn-group">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-expanded="false">
											Manage Account <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a
												href="employee_viewaccount.do?customerId=${customer.customerId}">View
													Account</a></li>
											<li><a
												href="employee_viewhistory.do?customerId=${customer.customerId}">Transaction
													History</a></li>
											<li><a
												href="employee_depositcheck.do?customerId=${customer.customerId}">Deposit
													Check</a></li>
											<li class="divider"></li>
											<li><a
												href="employee_setcustomerpsw.do?customerId=${customer.customerId}">Reset
													Password</a></li>
										</ul>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
	<noscript>
		<div class="row">
			<h3 class="form-signin-heading">${title}</h3>
			<h4 style="color: #CCFF66">${msg}</h4>
			<c:if test="${customers != null}">
				<div class="col-md-10 col-md-offset-1">

					<table class="table table-hover">
						<thead>
							<tr>
								<th>Email</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th style="text-align: right">Cash</th>
								<th style="text-align: center">Option</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="customer" items="${customers}">
								<tr>
									<td>${customer.email}</td>
									<td>${customer.firstName}</td>
									<td>${customer.lastName}</td>
									<td style="text-align: right">${customer.cash}</td>
									<td><a
										href="employee_viewaccount.do?customerId=${customer.customerId}">View
											Account</a> <a
										href="employee_viewhistory.do?customerId=${customer.customerId}">Transaction
											History</a> <a
										href="employee_depositcheck.do?customerId=${customer.customerId}">Deposit
											Check</a> <a
										href="employee_setcustomerpsw.do?customerId=${customer.customerId}">Reset
											Password</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</noscript>
</div>


<br />
<br />


<jsp:include page="employee_bottomtemplate.jsp" />