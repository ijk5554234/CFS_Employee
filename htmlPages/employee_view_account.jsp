

<jsp:include page="employee-toptemplate.jsp" />

	<table border="1">
	<thead>
		<tr>
		    <td align="center">Email</td>
		    <td align="center">First Name</td>
			<td align="center">Last Name</td>
			<td align="center">Address Line1</td>
			<td align="center">Address Line2</td>
			<td align="center">City</td>	
			<td align="center">Zip Code</td>
		</tr>
		</thead>
		<tbody>
		<tr>
            <td align="center">${customer.email}</td>
            <td align="center">${customer.firstName}</td>
            <td align="center">${customer.lastName}</td>
            <td align="center">${customer.addrLine1}</td>
            <td align="center">${customer.addrLine2}</td>
            <td align="center">${customer.city} </td>
           
            <td align="center">${customer.zip}</td>
        </tr></tbody>
		
	</table>

<hr />


<table border="1">
	<tr>
		<td style="font-size: x-large">Share Name</td>
		<td style="font-size: x-large">Share amount</td>
		<td style="font-size: x-large">Last trading price</td>
		<td style="font-size: x-large">Total value</td>
	</tr>
	<c:forEach var="record" items="${records}">
       <tr>
        <td ><a href="">${record.fundName}</a></td>
        <td >${record.share}</td>
        <td >${record.lastPrice}</td>
        <td >${record.originalPrice}</td>
    </tr>
    </c:forEach>
</table>

<jsp:include page="employee-bottomtemplate.jsp" />
