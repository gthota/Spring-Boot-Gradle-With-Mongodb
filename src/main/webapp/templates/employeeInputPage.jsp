
<style>
input[type="submit"] {
	margin-left: 178% !important;
}
</style>

<div data-ng-init="showInputPage()">
	<div>
		<div>
			<h1>Employee Input Details Page</h1>
		</div>
	</div>
	<div>
		<h3>
			Employee Input Details <img
				src="https://cdn4.iconfinder.com/data/icons/arrows-29/96/Arrows1_down-512.png"
				height="30px" width="30px" style="vertical-align: -10px;" />
		</h3>
	</div>
	<form method="post" name="saveForm" data-ng-submit="saveFormData()">
		<table>
			<tr>
				<td>Employee Id</td>
				<td><input type="number" data-ng-model="employeeId"
					name="employeeId" id="employeeId" required></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" data-ng-model="firstName" id="firstName"
					required="required"> <span id="firstNameErrorSpan"
					class="errorSpan"></span></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" data-ng-model="lastName" id="lastName"
					required="required"> <span id="lastNameErrorSpan"
					class="errorSpan"></span></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><input type="number" data-ng-model="phoneNumber"
					id="phoneNumber" required="required"> <span
					id="phoneErrorSpan" class="errorSpan"></span></td>
			</tr>
			<tr>
				<td>Email Id</td>
				<td><input type="text" data-ng-model="emailId" id="emailId"
					required="required"> <span id="emailErrorSpan"
					class="errorSpan"></span></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type="submit" name="Save" value="Save" /></td>
			</tr>
		</table>
	</form>

</div>
</html>
