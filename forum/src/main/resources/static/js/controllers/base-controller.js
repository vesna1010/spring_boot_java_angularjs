var app = angular.module('app');

app.controller('BaseController', [ '$scope', '$http', '$window',
		function($scope, $http, $window) {

			$scope.logOut = function() {
				$http({
					url : 'logout',
					method : 'POST'
				}).then(function(response) {
					$window.location.href = '#!/login';
				});
			}
		} ]);
