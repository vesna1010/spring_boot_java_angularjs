var app = angular.module('app');

app.controller('PostsController', [
		'$scope',
		'$filter',
		'$window',
		'post',
		'comments',
		'loggedUser',
		'PostsService',
		'CommentsService',
		function($scope, $filter, $window, post, comments, loggedUser,
				PostsService, CommentsService) {

			$scope.$parent.loggedUser = loggedUser;
			$scope.post = post;
			$scope.comments = comments;

			$scope.updatePost = function(post, id) {
				var promise = PostsService.updatePost(post, id);

				promise.then(function() {
					$window.location.reload();
				}, function(response) {
					if (response.status === 404) {
						alert(response.data.message);
					}
				});
			}

			$scope.deletePostById = function(id) {
				var promise = PostsService.deletePostById(id);

				promise.then(function(response) {
					$window.location.href = '#!/posts';
				}, function(response) {
					if (response.status === 404) {
						alert(response.data.message);
					}
				});
			}

			$scope.saveComment = function(comment) {
				comment.post = $scope.post;
				comment.user = $scope.loggedUser;
				comment.createdOn = $filter('date')(Date.now(),
						'dd.MM.yyyy HH:mm:ss');

				var promise = CommentsService.saveComment(comment);

				promise.then(function(response) {
					$window.location.reload();
				});
			}

			$scope.editComment = function(id) {
				$scope.comment = $scope.comments.filter(function(element) {
					return element.id === id;
				})[0];

				$scope.update = true;
			}
			
			$scope.cancel = function() {
				$scope.comment = undefined;
				$scope.update = false;
			}

			$scope.updateComment = function(comment, id) {
				var promise = CommentsService.updateComment(comment, id);

				promise.then(function() {
					$window.location.reload();
				}, function(response) {
					if (response.status === 404) {
						alert(response.data.message);
					}
				});
			}

			$scope.deleteCommentById = function(id) {
				var promise = CommentsService.deleteCommentById(id);

				promise.then(function(respons) {
					$window.location.reload();
				}, function(response) {
					if (response.status === 404) {
						alert(response.data.message);
					}
				});
			}

		} ]);