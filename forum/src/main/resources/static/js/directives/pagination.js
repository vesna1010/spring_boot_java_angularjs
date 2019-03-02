var app = angular.module('app');

app
		.directive(
				'pagination',
				function() {

					return {
						templateUrl : 'js/templates/pagination.html',
						restrict : 'E',
						scope : {
							pageNumber : '@',
							pageSize : '@',
							totalPages : '@',
							url : '@'
						},
						link : function(scope, element, attrs) {
							scope.pageNumber = Number(scope.pageNumber);
							scope.pageSize = Number(scope.pageSize);
							scope.totalPages = Number(scope.totalPages);
							scope.numbers = [];

							for (let i = scope.pageNumber - 2; i <= scope.pageNumber + 2; i++) {
								scope.numbers.push(i);
							}

							while (scope.numbers[0] < 0) {
								for (let i = 0; i < scope.numbers.length; i++) {
									scope.numbers[i] = scope.numbers[i] + 1;
								}
							}

							while (scope.numbers[scope.numbers.length - 1] >= scope.totalPages) {
								scope.numbers.pop();
							}

							while (scope.numbers.length < 5
									&& scope.numbers[0] > 0) {
								scope.numbers.unshift(scope.numbers[0] - 1);
							}
						}
					}

				});