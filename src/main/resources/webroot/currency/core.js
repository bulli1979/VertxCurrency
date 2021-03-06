
(function(){
	
	"use strict";
	
	var currencyWeb = angular.module('currencyWeb', ['ngResource', 'ui.router'])
		.config(function($stateProvider, $urlRouterProvider) {
			  $urlRouterProvider.otherwise("/home");
		});
})();




(function() {
	"use strict";

	angular.module('currencyWeb').factory(
			'CurrencyEndpoint',
			[
					'$resource',
					function($resource) {
						var CurrencyEndpoint = {};

						var options = {
							'query' : {
								isArray : false
							}
						};
//${rest-base-url}/getall
						var allPath = $resource('getCurrencys', {},
								{});
						//${rest-base-url}/change/:amount/:from/:to/
						var calculatePath = $resource(
								'calculate', {
									amount : "@amount",
									from : "@from",
									to : "@to"
								}, options);

						CurrencyEndpoint.getAll = function() {
							return allPath.query({});
						};

						CurrencyEndpoint.calculate = function(amount, from, to,
								succ, err) {
							
							return calculatePath.get({
								"amount" : amount,
								"from" : from,
								"to" : to
							}, succ, err);
						}

						return CurrencyEndpoint;
					} ]);
})();

(function() {

	"use strict";

	angular.module('currencyWeb').controller('HomeController',
			[ '$scope', 'CurrencyEndpoint', function($scope, CurrencyEndpoint) {
				var vm = this;

				vm.currencies = CurrencyEndpoint.getAll();
				
				vm.validate = function(){
					var amount = $scope.amount;
					var from = $scope.dataFrom.model;
					var to = $scope.dataTo.model;
					var correct = true;
					var tempErrorText = "";
					var match = /^[0-9]{1,8}$/g;
					if(!amount.match(match)){
						tempErrorText = "Bitte gebe einen Ganzzahlbetrag ein! Maximal 8 Zeichen!\n";
							correct = false;
					}
					if(from === null || from === "" || to === null || to === ""){
						tempErrorText += "Bitte fülle die Start und Zielwährung aus!\n";
							correct = false;
					}else if(from == to){
						tempErrorText += "Start und Zielwährung müssen unterschiedlich sein!\n";
							correct = false;
					}
					$scope.errortext = tempErrorText;
					return correct;
				}
				
				vm.getLangText = function(short){
					var name = "";
					vm.currencies.forEach(function(currency,index,arr) {
					    if(currency.code == short){
					    	name = currency.name;
					    }
					});
					return name
				}
				
				$scope.dataFrom = {
					model : null,
					availableOptions : vm.currencies
				};
				
				$scope.dataTo = {
						model : null,
						availableOptions : vm.currencies
				};

				$scope.amount = "";
				$scope.errortext = "";
				$scope.result = "";
				
				$scope.calculate = function() {					
					if(vm.validate()){
						$scope.errortext = "";
						vm.calculation = CurrencyEndpoint.calculate($scope.amount, $scope.dataFrom.model, $scope.dataTo.model, function(){
							$scope.result = $scope.amount + " " + vm.getLangText($scope.dataFrom.model) + " sind "  
							+ vm.calculation.result + " " + vm.getLangText($scope.dataTo.model);
						})
					}
					$scope.activeUI();
				}
				
				$scope.activeUI = function(){
					if(vm.validate()){
						$("#result").css("display","block");
						$("#error").css("display","none");
					}else{
						$("#error").css("display","block");
						$("#result").css("display","none");
					}
				}
			} ]);
	
})();

(function(){
	
	"use strict";
	
	
	angular.module('currencyWeb')
		.config([ '$stateProvider', '$urlRouterProvider' , function($stateProvider, $urlRouterProvider) {
			$stateProvider
			    .state('home', {
			      url: "/home",
			      templateUrl: "ui/Home/home.html",
			    });
			}]);
	
		  
})();

