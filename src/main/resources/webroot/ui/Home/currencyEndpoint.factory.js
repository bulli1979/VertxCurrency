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
						var allPath = $resource('getall.json', {},
								{});
						//${rest-base-url}/change/:amount/:from/:to/
						var calculatePath = $resource(
								'change.json', {
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
