var app = angular.module('app');

app.controller('RegisterUserController', [ '$scope', '$window', 'authorities',
		'loggedUser', 'UsersService',
		function($scope, $window, authorities, loggedUser, UserService) {

			$scope.$parent.loggedUser = loggedUser;
			$scope.$parent.active = 'register';
			$scope.user = {
				enabled : true,
				authority : 'USER'
			};
			$scope.authorities = authorities;

			$scope.saveUser = function(user) {
				var promise = UserService.saveUser(user);

				promise.then(function(response) {
					alert('Your data has been successfully saved.');
					$window.location.reload();
				});
			}

		} ]);