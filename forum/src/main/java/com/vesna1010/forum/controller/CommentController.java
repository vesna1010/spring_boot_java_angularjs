package com.vesna1010.forum.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
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
import com.vesna1010.forum.models.Comment;
import com.vesna1010.forum.services.CommentsService;

@RestController
public class CommentController {

	@Autowired
	private CommentsService service;

	@GetMapping("/comments/post/{id}")
	public List<Comment> findAllCommentsByPostId(@PathVariable Long id,
			@SortDefault(sort = "id", direction = Direction.DESC) Sort sort) {
		return service.findAllCommentsByPostId(id, sort);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public Comment saveComment(@RequestBody Comment comment) {
		return service.saveComment(comment);
	}

	@PreAuthorize("@commentsService.findCommentById(#id).user.email == #principal?.name")
	@GetMapping("/comments/{id}")
	public Comment findCommentById(@PathVariable Long id, Principal principal) {
		return service.findCommentById(id);
	}

	@PreAuthorize("@commentsService.findCommentById(#id).user.email == #principal?.name")
	@PutMapping("/comments/{id}")
	public Comment updateComment(@RequestBody Comment comment, @PathVariable Long id, Principal principal) {
		return service.updateComment(comment, id);
	}

	@PreAuthorize("@commentsService.findCommentById(#id).user.email == #principal?.name or hasAuthority('ADMIN')")
	@DeleteMapping("/comments/{id}")
	public void deleteCommentById(@PathVariable Long id, Principal principal) {
		service.deleteCommentById(id);
	}

}
