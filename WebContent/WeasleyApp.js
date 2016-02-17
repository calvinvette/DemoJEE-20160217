angular.module("WeasleyApp", ['ngRoute'])
.config(function($routeProvider) {
	$routeProvider
		.when('/', {
			controller: 'MainController', 
			templateUrl: 'views/main.html'
		})
		.when('/customer', {
			controller: 'CustomerController', 
			templateUrl: 'views/Customer.html'
		})
	;
});