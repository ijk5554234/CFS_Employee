<jsp:include page="employee-toptemplate.jsp" />
<p> Deposit Check </p>
<form method="GET" action="employee-depositCheck.do">
	<table>
		<th>
			<td>Customer Email</td>
			<td>Deposite Amount</td>
			<td>Confirm</td>
		</th>
		<tr>
			<td>
				<select name="email">
					 <c:forEach var="customer" items="${customers}">
        				<option value="${customer.email}">${customer.email}</option>
    				</c:forEach>
				</select>
			</td>
			<td>
				<input type="number" name="amount" />
			</td>
			<td>
			</td>

</form>
<table>
	<tr>
		<td> Customer Email:</td>
		<td> <input name = “customerEmail” type=“text”></td>
	</tr>
		<tr>
		<td> Customer Name:</td>
		<td> <input name = “customerName” type=“text”></td>
	</tr>
	<tr>
		<td> Amount:</td>
		<td> <input name = “depositAmount” type=“text”></td>
	</tr>
	<tr>
		<td> <button name = “depositConfirm” type=“button”>Deposit Confirm</button></td>
	</tr>
</table>
<jsp:include page="employee-bottomtemplate.jsp" />
