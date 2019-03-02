package com.vesna1010.forum.services;

import java.util.List;
import org.springframework.data.domain.Sort;
import com.vesna1010.forum.models.Comment;

public interface CommentsService {

	List<Comment> findAllCommentsByPostId(Long id, Sort sort);

	Comment findCommentById(Long id);

	Comment saveComment(Comment comment);

	Comment updateComment(Comment comment, Long id);

	void deleteCommentById(Long id);

}
