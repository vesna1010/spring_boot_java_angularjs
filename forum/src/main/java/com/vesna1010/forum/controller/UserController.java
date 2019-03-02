package com.vesna1010.forum.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.vesna1010.forum.enums.Authority;
import com.vesna1010.forum.models.User;
import com.vesna1010.forum.services.UsersService;

@RestController
public class UserController {

	@Autowired
	private UsersService service;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/users")
	public Page<User> findUsersByPage(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return service.findUsersByPage(pageable);
	}

	@GetMapping("/authenticated")
	public Object findLoggedUser(Principal principal) {
		return (principal != null) ? service.findUserByEmail(principal.getName()) : null;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/users")
	public User saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/users/update-password/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) {
		return service.updateUser(user, id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		service.deleteUserById(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/users/{id}")
	public void disableUserById(@PathVariable Long id) {
		service.disableUserById(id);
	}

	@GetMapping("/authorities")
	public Authority[] findAllAuthorities() {
		return Authority.values();
	}

}
