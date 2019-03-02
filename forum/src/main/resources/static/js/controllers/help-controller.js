var app = angular.module('app');

app.controller('HelpController', [ '$scope', 'loggedUser', 'message',
		function($scope, loggedUser, message) {

			$scope.$parent.loggedUser = loggedUser;
			$scope.message = message;

		} ]);