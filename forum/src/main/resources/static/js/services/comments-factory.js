var app = angular.module('app');

app.factory('CommentsService', [ '$http', function($http) {

	return {
		findAllComentsByPostId : findAllComentsByPostId,
		findCommentById : findCommentById,
		saveComment : saveComment,
		updateComment : updateComment,
		deleteCommentById : deleteCommentById
	}

	function findAllComentsByPostId(id) {
		return $http({
			url : 'comments/post/' + id,
			method : 'GET'
		});
	}

	function findCommentById(id) {
		return $http({
			url : 'comments/' + id,
			method : 'GET'
		});
	}

	function saveComment(comment) {
		return $http({
			url : 'comments',
			method : 'POST',
			data : comment
		});
	}

	function updateComment(comment, id) {
		return $http({
			url : 'comments/' + id,
			method : 'PUT',
			data : comment
		});
	}

	function deleteCommentById(id) {
		return $http({
			url : 'comments/' + id,
			method : 'DELETE'
		});
	}

} ]);