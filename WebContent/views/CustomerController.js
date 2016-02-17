angular.module("WeasleyApp")
.controller("CustomerController", ['$scope', function($scope) {
		$scope.customer = new Customer(); //
		$scope.registerCustomer = function() {
			console.log("Registered!!" + $scope.customer);
			// Call CustomerService to invoke a REST request to register on server.
		};
}]);