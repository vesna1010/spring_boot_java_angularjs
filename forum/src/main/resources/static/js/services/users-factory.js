var app = angular.module('app');

app.factory('UsersService', [ '$http', function($http) {

	return {
		findUsersByPage : findUsersByPage,
		findLoggedUser : findLoggedUser,
		saveUser : saveUser,
		updateUser : updateUser,
		deleteUserById : deleteUserById,
		disableUserById : disableUserById,
		findAllAuthorities : findAllAuthorities
	}

	function findUsersByPage(params) {
		return $http({
			url : 'users',
			method : 'GET',
			params : {
				page : params.page,
				size : params.size,
				sort : 'id,asc'
			}
		});
	}

	function findLoggedUser() {
		return $http({
			url : 'authenticated',
			method : 'GET'
		});
	}

	function saveUser(user) {
		return $http({
			url : 'users',
			method : 'POST',
			data : user
		});
	}

	function updateUser(user, id) {
		return $http({
			url : 'users/update-password/' + id,
			method : 'PUT',
			data : user
		});
	}

	function deleteUserById(id) {
		return $http({
			url : 'users/' + id,
			method : 'DELETE'
		});
	}

	function disableUserById(id) {
		return $http({
			url : 'users/' + id,
			method : 'PUT'
		});
	}

	function findAllAuthorities() {
		return $http({
			url : 'authorities',
			method : 'GET'
		});
	}

} ]);