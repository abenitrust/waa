package com.waa.lab.service;

import com.waa.lab.domain.Comment;
import com.waa.lab.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(long id);
    void save(CommentDto cDto);
    void update(long id, CommentDto cDto);
    void delete(long id);
}
