package com.vesna1010.forum.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vesna1010.forum.models.Post;

public interface PostsService {

	Page<Post> findPostsByPage(Pageable pageable);

	Post findPostById(Long id);

	Post savePost(Post post);

	Post updatePost(Post post, Long id);

	void deletePostById(Long id);

}
