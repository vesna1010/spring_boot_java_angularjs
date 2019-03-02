var app = angular.module('app');

app.controller('LoginController', [ '$scope', '$http', '$window', 'loggedUser',
		function($scope, $http, $window, loggedUser) {

			$scope.$parent.loggedUser = loggedUser;

			$scope.logIn = function(email, password) {
				$http({
					url : 'login',
					method : 'POST',
					params : {
						email : email,
						password : password
					}
				}).then(function(response) {
					if (response.status === 200) {
						$window.location.href = '#!/';
					}
				}, function(response) {
					if (response.status == 401) {
						$scope.message = response.data.message;
						$scope.email = '';
						$scope.password = '';
					}
				});

			}

		} ]);