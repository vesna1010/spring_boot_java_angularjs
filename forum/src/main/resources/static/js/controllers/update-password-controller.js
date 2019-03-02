var app = angular.module('app');

app.controller('PasswordController', [ '$scope', '$window', 'loggedUser',
		'UsersService', function($scope, $window, loggedUser, UserService) {

			$scope.$parent.loggedUser = loggedUser;
			$scope.$parent.active = 'password';
			
			$scope.updateUser = function(user, id) {
				var promise = UserService.updateUser(user, id);

				promise.then(function(response) {
					alert('Your data has been successfully saved.');
					$window.location.reload();
				}, function(response) {
					if (response.status === 404) {
						alert(response.data.message);
					}
				});
			}

		} ]);