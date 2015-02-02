<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee_toptemplate.jsp" />

<div id="body" class="container">
	<div class="row">
		<h3 class="form-signin-heading">${title}</h3>
		<jsp:include page="error-list.jsp" />
		<br /> <br />

			<div class="col-md-10 col-md-offset-1">

				<table class="table table-hover">
					<thead>
						<tr>
							<th>Email</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th style="text-align:right">cash</th>
							<th>Address Line 1</th>
							<th>Address Line 2</th>
							<th>City</th>
							<th>State</th>
							<th>Zip</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${customer.email}</td>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
							<td style="text-align:right">${cash}</td>
							<td>${customer.addrLine1}</td>
							<td>${customer.addrLine2}</td>
							<td>${customer.city}</td>
							<td>${customer.state}</td>
							<td>${customer.zip}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<br />
			<br />
			<br />
			<div class="col-md-10 col-md-offset-1">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Share Name</th>
							<th>Share amount</th>
							<th>Last trading price</th>
							<th>Total value</th>
						</tr>
					</thead>
					<c:forEach var="record" items="${records}">
						<tr>
							<td><a href="">${record.fundName}</a></td>
							<td>${record.share}</td>
							<td>${record.lastPrice}</td>
							<td>${record.share*record.lastPrice}</td>
						</tr>
					</c:forEach>
				</table>
			</div>

	</div>
</div>


<br />
<br />


<jsp:include page="employee_bottomtemplate.jsp" />