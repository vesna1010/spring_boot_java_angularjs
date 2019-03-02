var app = angular.module('app');

app.controller('UsersPageController', [ '$scope', '$window', 'page',
		'loggedUser', 'UsersService',
		function($scope, $window, page, loggedUser, UserService) {

			$scope.$parent.loggedUser = loggedUser;
			$scope.$parent.active = 'users';
			$scope.users = page.content;
			$scope.pageNumber = page.number;
			$scope.pageSize = page.size;
			$scope.totalPages = page.totalPages;

			$scope.deleteUserById = function(id) {
				var promise = UserService.deleteUserById(id);

				promise.then(function(response) {
					$window.location.reload();
				}, function(response) {
					if (response.status === 404) {
						alert(response.data.message);
					}
				});
			}

			$scope.disableUserById = function(id) {
				var promise = UserService.disableUserById(id);

				promise.then(function(response) {
					$window.location.reload();
				}, function(response) {
					if (response.status === 404) {
						alert(response.data.message);
					}
				});
			}

		} ]);