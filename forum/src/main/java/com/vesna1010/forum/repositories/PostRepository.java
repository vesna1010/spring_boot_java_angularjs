package com.vesna1010.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vesna1010.forum.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
