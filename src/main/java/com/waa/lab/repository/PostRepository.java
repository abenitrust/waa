package com.waa.lab.repository;

import com.waa.lab.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();

    @Query("select p from Post p Join p.comments c where c.id = :commentId")
    Optional<Post> findCommentedPostByCommentId(long commentId);

    @Query("select p from Post p where p.title = :title")
    List<Post> findByTitle(String title);
}
