package com.waa.lab.service.impl;

import com.waa.lab.domain.Comment;
import com.waa.lab.domain.Post;
import com.waa.lab.domain.dto.CommentDto;
import com.waa.lab.repository.CommentRepository;
import com.waa.lab.repository.PostRepository;
import com.waa.lab.repository.UserRepository;
import com.waa.lab.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CommentDto cDto) {
        userRepository.findPosterByPostId(cDto.getPostId())
            .ifPresent(user -> {
                List<Post> posts = user.getPosts();
                posts.stream()
                    .filter(p -> p.getId() == cDto.getPostId())
                    .findFirst()
                    .ifPresent( post -> {
                        List<Comment> comments = post.getComments();
                        Comment c = new Comment();
                        modelMapper.map(cDto, c);
                        comments.add(c);
                        post.setComments(comments);
                        user.setPosts(posts);
                        userRepository.save(user);
                });

            });
    }

    @Override
    public void update(long id, CommentDto cDto) {
        userRepository.findPosterByPostId(cDto.getPostId())
            .ifPresent(user -> {
                List<Post> posts = user.getPosts();
                posts.stream()
                    .filter(post -> post.getId() == cDto.getPostId())
                    .findFirst()
                    .ifPresent(post -> {
                        var comments = post.getComments();
                        comments.stream()
                            .filter(comment -> comment.getId() == id)
                            .findFirst()
                            .ifPresent(comment -> {
                                modelMapper.map(cDto, comment);
                                comment.setId(id);
                                post.setComments(comments);
                                user.setPosts(posts);
                                userRepository.save(user);
                            });
                    });
            });
    }

    @Override
    public void delete(long id) {
        commentRepository.findById(id)
            .ifPresent(comment -> {
                commentRepository.delete(comment);
            });
    }
}
