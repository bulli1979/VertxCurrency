(function(){
	"use strict";	
	angular.module('currencyWeb')
		.config([ '$stateProvider', function($urlRouterProvider) {
			$stateProvider
			    .state('home', {
			      url: "/home",
			      templateUrl: "ui/Home/home.html",
			    });
			}]);		  
})();
