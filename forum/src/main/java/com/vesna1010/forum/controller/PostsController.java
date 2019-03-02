package com.vesna1010.forum.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
import com.vesna1010.forum.models.Post;
import com.vesna1010.forum.services.PostsService;

@RestController
public class PostsController {

	@Autowired
	private PostsService service;

	@GetMapping("/posts")
	public Page<Post> findPostsByPage(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
		return service.findPostsByPage(pageable);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/posts")
	@ResponseStatus(HttpStatus.CREATED)
	public Post savePost(@RequestBody Post post) {
		return service.savePost(post);
	}

	@PreAuthorize("@postsService.findPostById(#id).user.email == #principal?.name")
	@PutMapping("/posts/{id}")
	public Post updatePost(@RequestBody Post post, @PathVariable Long id, Principal principal) {
		return service.updatePost(post, id);
	}

	@GetMapping("/posts/{id}")
	public Post findPostById(@PathVariable Long id) {
		return service.findPostById(id);
	}

	@PreAuthorize("@postsService.findPostById(#id).user.email == #principal?.name or hasAuthority('ADMIN')")
	@DeleteMapping("/posts/{id}")
	public void deletePostById(@PathVariable Long id, Principal principal) {
		service.deletePostById(id);
	}

}
