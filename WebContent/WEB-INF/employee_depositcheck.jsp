<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee_toptemplate.jsp" />

<div id="body" class="container">
    <div class="row">
        <h3 class="form-signin-heading">${title}</h3>
        <br/><br/>
        <h4 style="color: #CCFF66">${msg}</h4>
        <jsp:include page="error-list.jsp" />
        <div id="chpw_form">
            <form method="POST" action="employee_depositcheck.do">
            <input value= "${customerId}" name="customerId" type="hidden">

                <div class="input-group col-md-6 col-md-offset-3">
                    <div class="form-group has-feedback">
                        <span class="form-control-feedback fui-credit-card"></span> <input
                            type="text" name="amount" class="form-control flat input-hg"
                            placeholder="Deposit Amount" aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="input-group col-md-6 col-md-offset-3">
                    <input type="submit" class="btn btn-primary btn-hg btn-block flat"
                        name="action" value="Deposit Confirm" />
                </div>
            </form>
        </div>
    </div>
</div>

<br />
<br />
<br />

<jsp:include page="employee_bottomtemplate.jsp" />