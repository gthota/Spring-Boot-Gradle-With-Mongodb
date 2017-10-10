
<html>

<body>

	<div data-ng-init="showListPage()">
		<div>
			<h2>${message}</h2>

			<div>
				<form method="post" name="searchForm"
					data-ng-submit="searchFormData()">
					<table>
						<tr>
							<td>Employee Id</td>
							<td><input type="text" data-ng-model="employeeId" /></td>
						</tr>
						<tr>
							<td>Email Id</td>
							<td><input type="text" data-ng-model="emailId" /></td>
						</tr>
						<tr>
							<td>First Name</td>
							<td><input type="text" data-ng-model="firstName" /></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><input type="text" data-ng-model="lastName" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="Search"
								style="margin-left: 62%" /></td>
						</tr>
					</table>
				</form>

			</div>

			<h3>
				Below is the list of employee's details saved in the Database <img
					src="https://cdn4.iconfinder.com/data/icons/arrows-29/96/Arrows1_down-512.png"
					height="30px" width="30px" style="vertical-align: -10px;" />
			</h3>
			<table id="listPageTable">

				<tr id="header">

					<td>Employee Id</td>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Phone Number</td>
					<td>Email Id</td>
					<td>Actions</td>

				</tr>

				<tr data-ng-repeat="employee in employeeList">
					<td>{{employee.employeeId}}</td>
					<td>{{employee.firstName}}</td>
					<td>{{employee.lastName}}</td>
					<td>{{employee.phoneNumber}}</td>
					<td>{{employee.emailId}}</td>
					<td><a href="#"
						data-ng-click="viewEmployee(employee.employeeId)"> <img
							src="https://cdn4.iconfinder.com/data/icons/meBaze-Freebies/512/edit-notes.png"
							title="View/Edit" height="40px" width="40px" />
					</a> <a href="#" data-ng-click="deleteEmployee(employee.employeeId)">
							<img
							src="https://cdn2.iconfinder.com/data/icons/round-interface-1/217/50-512.png"
							title="Delete" height="40px" width="40px" />
					</a></td>
				</tr>

			</table>

		</div>
	</div>
</body>

</html>