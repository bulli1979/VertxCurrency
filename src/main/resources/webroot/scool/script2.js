(function(){
	
	"use strict";

	angular.module('scoolapp',[]).controller('controller1',function($scope,$http) {
			$http.get("countries.json")
		    .then(function(response) {
		    	$scope.countrys  = response.data;
		    });
			
			$scope.select = function() {	
				$scope.selected = this.country.code + " : " + this.country.name;
			}
			
			
		});
})();

