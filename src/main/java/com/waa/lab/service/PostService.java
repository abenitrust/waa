package com.waa.lab.service;

import com.waa.lab.domain.Post;
import com.waa.lab.domain.dto.PostDto;
import com.waa.lab.repository.PostRepository;
import com.waa.lab.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(PostDto p) {
        userRepository.findById(p.getUserId())
            .ifPresent( user -> {
                Post post = new Post();
                modelMapper.map(p, post);

                List<Post> posts = user.getPosts();
                posts.add(post);
                user.setPosts(posts);

                userRepository.save(user);
            });
    }

    @Override
    public void delete(long id) {
        postRepository.findById(id)
            .ifPresent( pst -> postRepository.delete(pst));

    }

    @Override
    public Post getById(long id) {
        return postRepository.findById(id).orElse(null);

    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public void update(long id, PostDto p) {
        userRepository.findById(p.getUserId()).ifPresent( user -> {
                List<Post> posts = user.getPosts();
                posts.stream()
                    .filter(pst -> pst.getId() == id)
                    .findFirst()
                    .ifPresent( post -> {
                        modelMapper.map(p, post);
                        post.setId(id);
                        user.setPosts(posts);
                        userRepository.save(user);
                    });
        });
    }

    @Override
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }
}
