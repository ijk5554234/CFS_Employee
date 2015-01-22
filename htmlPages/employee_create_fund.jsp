<jsp:include page="employee-toptemplate.jsp" />
<title> Employee - Create Fund</title>

<form method="POST" action="createFund.do">
       
    <div class="fund_details_fields">
    <label for="fundname"> Fund Name <span class="mandatory">*</span></label><br>
		<input id="fundname" maxlength="35" name="fundName" value="" type="text" size="20"><input name="_D:fundname" value=" " type="hidden">
	</div>
	
	<div class="fund_details_fields">
		<label for="ticker"> ticker <span class="mandatory">*</span></label><br>
		<input id="ticker" maxlength="5" name="symbol" value="" type="text" size="50"><input name="_D:ticker" value=" " type="hidden">
	</div>
	<div id="error_field">
	 <c:forEach var="error" items="${errors}">
        <p>${error}</p>
    </c:forEach>

<br>
<input type="submit" name="button" value="Create"/>    
                
</form>
      <jsp:include page="employee-bottomtemplate.jsp" />