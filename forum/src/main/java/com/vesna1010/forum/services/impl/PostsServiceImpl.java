package com.vesna1010.forum.services.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vesna1010.forum.exceptions.ResourceNotFoundException;
import com.vesna1010.forum.models.Post;
import com.vesna1010.forum.repositories.PostRepository;
import com.vesna1010.forum.services.PostsService;

@Service("postsService")
@Transactional
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostRepository repository;

	@Override
	public Page<Post> findPostsByPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Post findPostById(Long id) {
		Optional<Post> optional = repository.findById(id);

		return optional.orElseThrow(() -> new ResourceNotFoundException("No post with id " + id));
	}

	@Override
	public Post savePost(Post post) {
		return repository.save(post);
	}

	@Override
	public Post updatePost(Post post, Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("No post with id " + id);
		}

		return repository.save(post);
	}

	@Override
	public void deletePostById(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("No post with id " + id);
		}

		repository.deleteById(id);
	}

}
