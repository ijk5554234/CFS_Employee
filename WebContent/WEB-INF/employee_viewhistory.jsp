<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="employee_toptemplate.jsp" />

<div id="body" class="container">
	<div class="row">
		<h3 class="form-signin-heading">${title}</h3>
		<h4 style="color: #CCFF66">${msg}</h4>
		<jsp:include page="error-list.jsp" />
		<br /> <br />

		<div class="col-md-10 col-md-offset-1">

			<table class="table table-hover">
				<thead>
					<tr>
						<td>TransactionId</td>
						<td>Type</td>
						<td>FundName</td>
						<td style="text-align:right">Share</td>
						<td style="text-align:right">Price</td>
						<td style="text-align:right">Amount</td>
						<td>Date</td>
					</tr>
				</thead>
				<c:forEach var="record" items="${records}">
					<tr>
						<td>${record.transactionId}</td>
						<td>${record.type}</td>
						<td><a href="">${record.fundName}</a></td>
						<td style="text-align:right">${record.share}</td>
						<td style="text-align:right">${record.price}</td>
						<td style="text-align:right">${record.amount}</td>
						<td>${record.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<br />
<br />
<br />


<jsp:include page="employee_bottomtemplate.jsp" />