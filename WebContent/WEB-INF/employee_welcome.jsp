<jsp:include page="employee_toptemplate.jsp" />
<br />
<br />
<h2>Welcome ${employee.firstName} ${employee.lastName}</h2>
<body>
	<p>
		There are totally ${customerSum} registered customer;<br> There
		are totally ${fundSum} funds;<br> There are totally ${totalTrans}
		pending transactions;<br>
	</p>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Type</th>
						<th style="text-align:right">Numbers of transaction</th>
						<th style="text-align:right">Amount</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Buy</td>
						<td style="text-align:right">${buy}</td>
						<td style="text-align:right">$${buySum}</td>
					</tr>
					<tr>
						<td>Sell</td>
						<td style="text-align:right">${sell}</td>
						<td style="text-align:right">${sellSum} shares</td>
					</tr>
					<tr>
						<td >RequestCheck</td>
						<td style="text-align:right">${requestCheck}</td>
						<td style="text-align:right">$${requestSum}</td>
					</tr>
					<tr>
						<td>DepositCheck</td>
						<td style="text-align:right">${depositCheck}</td>
						<td style="text-align:right">$${depositSum}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<p id="pie" style="display: none">${buy},${sell},${requestCheck},${depositCheck}</p>
	<p id="bar" style="display: none">${buydouble},${requestdouble},${depositdouble}</p>
	<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<div class="col-md-6">
		<h5>Pending Transactions Distribution</h5>
			<canvas id="myPieChart" width="400" height="400"></canvas>
		</div>
		<div class="col-md-6">
		<h5>Transaction Amount</h5>
			<canvas id="myBarChart" width="400" height="400"></canvas>
		</div>
	</div>
	</div>s

</body>

<jsp:include page="employee_bottomtemplate.jsp" />