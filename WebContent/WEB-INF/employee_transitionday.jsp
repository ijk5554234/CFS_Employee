<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="employee_toptemplate.jsp" />
<div id="body" class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<h3 class="form-signin-heading">Transition Day</h3>
			<c:choose>
				<c:when test="${ (empty msg) }">
				</c:when>
				<c:otherwise>
					<h4 style="color: #CCFF66">${msg}</h4>
				</c:otherwise>
			</c:choose>

			<c:forEach var="error" items="${errors}">
				<h3 style="color: red">${error}</h3>
			</c:forEach>

			<form role="form" method="POST">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<h4>Funds to Update</h4>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Fund Name</th>
										<th>Ticker</th>
										<th style="text-align:right">Last Price</th>
										<th style="text-align:center">Price per Share</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="fund" items="${fundList}">
										<tr>
											<td>${fund.fundName }</td>
											<td>${fund.symbol }</td>
											<td style="text-align:right">${prices.get(fund.fundId)}</td>
											<td>
												<div style="width:250px; align:right" class="form-group input-group">
													<span class="input-group-addon">$</span> <input
														name="fund_${fund.fundId}" type="text" required
														class="form-control" style="width:250px; text-align:right" value="${prices.get(fund.fundId)}">
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<h3>The last trading day is: ${ lastDay }.</h3>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Trading Day: (yyyy-mm-dd)</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input name="date" type="date" required
										class="form-control flat input-hg" value="${ lastDay }"></td>
								</tr>
							</tbody>
						</table>
						<button type="submit" name="action" value="create"
							class="btn btn-primary">Submit Transition Day</button>
					</div>
				</div>
				<br>

			</form>

		</div>
	</div>
</div>


<jsp:include page="employee_bottomtemplate.jsp" />