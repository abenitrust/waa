package com.waa.lab.repository;

import com.waa.lab.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    @Query( "select u from User u where u.posts.size >= :num" )
    List<User> findUserWithPosts(int num);

    @Query("select u from User u JOIN u.posts p Where p.id = :post_id")
    Optional<User> findPosterByPostId(long post_id);

    @Query("select u from User u Join u.posts p where p.title = :title")
    List<User> findByPostTitle(String title);

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
