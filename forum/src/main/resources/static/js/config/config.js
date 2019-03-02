var app = angular.module('app');

app.config([ '$routeProvider', function($routeProvider) {
	
	$routeProvider.when('/', {
		redirectTo : '/home'
	})
	
	.when('/home/:page?/:size?', {
		templateUrl : 'js/templates/home.html',
		controller : 'HomeController',
		resolve : {
			loggedUser : function(UsersService) {
				return UsersService.findLoggedUser().then(
						function(response) {
							return response.data;
						});
			},
			page : function(PostsService, $route) {
				return PostsService.findPostsByPage($route.current.params).then(
						function(response) {
							return response.data;
						});
			}
		}
	})
	
	.when('/posts/:id', {
		templateUrl : 'js/templates/post-page.html',
		controller : 'PostsController',
		resolve : {
			loggedUser : function(UsersService) {
				return UsersService.findLoggedUser().then(
						function(response) {
							return response.data;
						});
			},
			post : function(PostsService, $route, $location) {
				return PostsService.findPostById($route.current.params.id).then(
						function(response) {
							return response.data;
						}, function(response) {
							if(response.status === 404) {
								$location.path('/not-found/' + response.data.message);
							}
						});
			},
			comments : function(CommentsService, $route) {
				return CommentsService.findAllComentsByPostId($route.current.params.id).then(
						function(response) {
							return response.data;
						});
			}
		}
	})
	
	.when('/users/:page?/:size?', {
		templateUrl : 'js/templates/users.html',
		controller : 'UsersPageController',
		resolve : {
			loggedUser : function(UsersService, $location) {
				return UsersService.findLoggedUser().then(
						function(response) {
							if(!response.data) {
								$location.path('/login');
							} 
							else if(response.data.authority !== 'ADMIN'){
								$location.path('/denied');
							}	
							
							return response.data;
						});
			},
			page : function(UsersService, $route, $location) {
				return UsersService.findUsersByPage($route.current.params).then(
						function(response) {
							return response.data;
						});
			}
			
		}
	})
	
	.when('/register-user', {
		templateUrl : 'js/templates/user-form.html',
		controller : 'RegisterUserController',
		resolve : {
			loggedUser : function(UsersService) {
				return UsersService.findLoggedUser().then(
						function(response) {
							return response.data;
						});
			},
			authorities : function(UsersService) {
				return UsersService.findAllAuthorities().then(function(response) {
					return response.data;
				});
			}
		}
	})

	.when('/update-password', {
		templateUrl : 'js/templates/update-password-form.html',
		controller : 'PasswordController',
		resolve : {
			loggedUser : function(UsersService, $location) {
				return UsersService.findLoggedUser().then(
						function(response) {
							if(!response.data) {
								$location.path('/login');
							}
							
							return response.data;
						});
			},
		}
	})
	
	.when('/login', {
		templateUrl : 'js/templates/login-form.html',
		controller : 'LoginController',
		resolve : {
			loggedUser : function(UsersService) {
				return UsersService.findLoggedUser().then(
						function(response) {
							return response.data;
						});
			}
		}
	})
	
	.when('/denied',  {
		templateUrl : 'js/templates/exceptions.html',
		controller : 'HelpController',
		resolve : {
			loggedUser : function(UsersService) {
				return UsersService.findLoggedUser().then(
						function(response) {
							return response.data;
						});
			},
			message : function() {
				return 'Access Denied';
			}
		}
	})
	
	.when('/not-found/:msg',  {
		templateUrl : 'js/templates/exceptions.html',
		controller : 'HelpController',
		resolve : {
			loggedUser : function(UsersService) {
				return UsersService.findLoggedUser().then(
						function(response) {
							return response.data;
						});
			},
			message : function($route) {
				return $route.current.params.msg;
			}
		}
	})
	;

} ]);