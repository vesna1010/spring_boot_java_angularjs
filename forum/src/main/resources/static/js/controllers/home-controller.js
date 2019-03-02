var app = angular.module('app');

app.controller('HomeController', [
		'$scope',
		'$filter',
		'$window',
		'PostsService',
		'page',
		'loggedUser',

		function($scope, $filter, $window, PostsService, page, loggedUser) {

			$scope.$parent.loggedUser = loggedUser;
			$scope.posts = page.content;
			$scope.pageNumber = page.number;
			$scope.pageSize = page.size;
			$scope.totalPages = page.totalPages;

			$scope.savePost = function(post) {
				post.user = $scope.loggedUser;
				post.createdOn = $filter('date')(Date.now(), 'dd.MM.yyyy HH:mm:ss');

				var promise = PostsService.savePost(post);

				promise.then(function(response) {
					$window.location.href = '#!/';
				}, function(response) {
					alert('Error');
				});
			}

		} ]);