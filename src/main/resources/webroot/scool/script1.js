(function(){
	
	"use strict";

	angular.module('scoolapp',[]).controller('controller1',
		[ '$scope', function($scope) {
			$scope.countrys = ["Schweiz","Deutschland","Schweden","Frankreich"];
			
			$scope.select = function() {	
				$scope.selected = this.country;
			}
			
			
		}]);
})();

