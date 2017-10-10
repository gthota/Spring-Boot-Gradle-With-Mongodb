
<style>
input[type="submit"] {
	margin-left: 138% !important;
}
</style>
<div>

	<h1>Employee View page</h1>
	<h3>
		View Employee Details <img
			src="https://cdn4.iconfinder.com/data/icons/arrows-29/96/Arrows1_down-512.png"
			height="30px" width="30px" style="vertical-align: -10px;" />
	</h3>

	<form method="post" name="updateForm" data-ng-submit="updateFormData()">
		<table>
			<tr>
				<td>Employee Id</td>
				<td><input type="text" data-ng-model="employee.employeeId"
					readonly="readonly" required="required"></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" data-ng-model="employee.firstName"
					id="firstName" required="required"></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" data-ng-model="employee.lastName"
					id="lastName" required="required"></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><input type="text" data-ng-model="employee.phoneNumber"
					id="phoneNumber" required="required"></td>
			</tr>
			<tr>
				<td>Email Id</td>
				<td><input type="text" data-ng-model="employee.emailId"
					id="emailId" required="required"></td>
			</tr>
		</table>

		<table>
			<tr>
				<td><input type="submit" name="Update" value="Update" /></td>
			</tr>
		</table>
	</form>
</div>

