<jsp:include page="employee-toptemplate.jsp" />

<p style="font-size: medium">Enter your new password</p>


<p>
<form method="POST" action="change-pwd.do">
	<table>
	<tr>
			<td>Customer Email:</td>
			<td><input type="email" name="email" value="" /></td>
		</tr>
		<tr>
			<td>New Password:</td>
			<td><input type="password" name="newPassword" value="" /></td>
		</tr>
		<tr>
			<td>Confirm New Password:</td>
			<td><input type="password" name="confirmPassword" value="" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				name="button" value="Change Password" /></td>
		</tr>
	</table>
</form>
</p>

<jsp:include page="employee-bottomtemplate.jsp" />
