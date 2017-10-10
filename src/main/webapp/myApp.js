/**
 * 
 */

window.onpopstate = function(e) {
	window.history.forward(1);
}
var app = angular.module('loginApp', [ "ngRoute" ]);

app.config(function($routeProvider, $locationProvider) {

	$locationProvider.hashPrefix('');
	$routeProvider

	.when("/homePage", {
		templateUrl : "templates/homePage.jsp",
		controller : "loginController"
	}).when("/loginPage", {
		templateUrl : "templates/loginPage.jsp",
		controller : "loginController"
	})

	.when("/inputPage", {
		templateUrl : "templates/employeeInputPage.jsp",
		controller : "loginController"
	})

	.when("/listPage", {
		templateUrl : "templates/employeeList.jsp",
		controller : "loginController"
	})

	.when("/viewPage", {
		templateUrl : "templates/employeeViewPage.jsp",
		controller : "loginController"
	})

	.otherwise({
		redirectTo : '/loginPage'
	});

});

app
		.controller(
				"loginController",
				function($scope, $location, $http, $rootScope) {

					$rootScope.showHomePage = function() {

						$http.get("/home").then(function(response) {
							$rootScope.message = response.data.message;
							$location.path("/homePage");
						});

					}

					$rootScope.showInputPage = function() {

						$http.get("/input").then(function(response) {
							$rootScope.message = response.data.message;
							$location.path("/inputPage");
						});

					}

					$rootScope.showListPage = function() {

						$http
								.get("/search")
								.then(
										function(response) {
											$rootScope.employeeList = response.data.employeeList;
											$location.path("/listPage");

										});
					}
					
					
					
					
					$rootScope.searchFormData = function() {
						
						var data = {
								'employeeId' : $scope.employeeId,
								'emailId' : $scope.emailId,
								'firstName' : $scope.firstName,
								'lastName' : $scope.lastName
								
							};

						$http
								.post("/searchDetails", data)
								.then(
										function(response) {
											$rootScope.employeeList = response.data.employeeList;
											$location.path("/listPage");

										});
					}

					
					
					
					
					$rootScope.logout = function() {

						$location.path("/loginPage");

					}

					$scope.authenticate = function() {

						var data = {
							'username' : $scope.username,
							'password' : $scope.password
						};

						$http
								.post("authenticateUser", data)
								.then(
										function(response) {
											if (response.data.message == "success") {

												$rootScope.message = response.data.message;
												$rootScope.employeeObject = response.data.employee;

												// alert(JSON.stringify($rootScope.employeeObject));

												$location.path("/homePage");
											} else {
												$rootScope.message = response.data.message;
												alert($rootScope.message);
											}
										});
					}

					$rootScope.updateFormData = function() {

						var data = {
							'employeeId' : $scope.employee.employeeId,
							'firstName' : $scope.employee.firstName,
							'lastName' : $scope.employee.lastName,
							'phoneNumber' : $scope.employee.phoneNumber,
							'emailId' : $scope.employee.emailId

						};

						$http
								.post("/save", data)
								.then(
										function(response) {
											$rootScope.employeeList = response.data.employeeList;
											$location.path("/listPage");
										});

					}

					$rootScope.saveFormData = function() {

						var data = {
							'employeeId' : $scope.employeeId,
							'firstName' : $scope.firstName,
							'lastName' : $scope.lastName,
							'phoneNumber' : $scope.phoneNumber,
							'emailId' : $scope.emailId

						};

						$http
								.post("/save", data)
								.then(
										function(response) {
											$rootScope.employeeList = response.data.employeeList;
											$location.path("/listPage");
										});

					}

					$rootScope.viewEmployee = function(employeeId) {
						
						$http
								.post("/view?employeeId=" + employeeId)
								.then(
										function(response) {
											$rootScope.employee = response.data.employee;
											$location.path("/viewPage");
										});
					}

					$rootScope.deleteEmployee = function(employeeId) {

						if (confirm("Are you sure you want to delete the record?")) {

							$http
									.post("/delete?employeeId=" + employeeId)
									.then(
											function(response) {
												$rootScope.employeeList = response.data.employeeList;
												$location.path("/listPage");
											});

						}else{
							$rootScope.showListPage();
						}
					}

				});
