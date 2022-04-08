package com.waa.lab.service;

import com.waa.lab.domain.Post;
import com.waa.lab.domain.dto.PostDto;

import java.util.List;

public interface PostService {
    void save(PostDto p);
    void delete(long id);
    Post getById(long id);
    List<Post> getAll();
    void update(long id, PostDto p);
    List<Post> findByTitle(String title);
}
