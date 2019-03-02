var app = angular.module('app');

app.factory('PostsService', [ '$http', function($http) {

	return {
		findPostsByPage : findPostsByPage,
		findPostById : findPostById,
		savePost : savePost,
		updatePost : updatePost,
		deletePostById : deletePostById
	}

	function findPostsByPage(params) {
		return $http({
			url : 'posts',
			method : 'GET',
			params : {
				page : params.page,
				size : params.size,
				sort : 'id,desc'
			}
		});
	}

	function findPostById(id) {
		return $http({
			url : 'posts/' + id,
			method : 'GET'
		});
	}

	function savePost(post) {
		return $http({
			url : 'posts',
			method : 'POST',
			data : post
		});
	}

	function updatePost(post, id) {
		return $http({
			url : 'posts/' + id,
			method : 'PUT',
			data : post
		});
	}

	function deletePostById(id) {
		return $http({
			url : 'posts/' + id,
			method : 'DELETE'
		});
	}

} ]);