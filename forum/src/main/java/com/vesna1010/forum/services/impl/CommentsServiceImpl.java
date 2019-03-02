package com.vesna1010.forum.services.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.vesna1010.forum.exceptions.ResourceNotFoundException;
import com.vesna1010.forum.models.Comment;
import com.vesna1010.forum.repositories.CommentRepository;
import com.vesna1010.forum.services.CommentsService;

@Service("commentsService")
@Transactional
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentRepository repository;

	@Override
	public List<Comment> findAllCommentsByPostId(Long id, Sort sort) {
		return repository.findAllByPostId(id, sort);
	}

	@Override
	public Comment findCommentById(Long id) {
		Optional<Comment> optional = repository.findById(id);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No comment with id " + id));
	}

	@Override
	public Comment saveComment(Comment comment) {
		return repository.save(comment);
	}

	@Override
	public Comment updateComment(Comment comment, Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("No comment with id " + id);
		}

		return repository.save(comment);
	}

	@Override
	public void deleteCommentById(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("No comment with id " + id);
		}

		repository.deleteById(id);
	}

}
