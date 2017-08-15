(function(){
	"use strict";
	var currencyWeb = angular.module('currencyWeb', ['ngResource'])
		.config(function($urlRouterProvider) {
			  $urlRouterProvider.otherwise("/home");
		});
})();



