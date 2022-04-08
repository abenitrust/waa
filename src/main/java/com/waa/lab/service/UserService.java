package com.waa.lab.service;

import com.waa.lab.domain.User;
import com.waa.lab.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    void save(UserDto u);
    void delete(long id);
    User getById(long id);
    List<User> getAll();
    void update(long id, UserDto u);
    List<User> findUserWithPosts(int numberOfPosts);
    List<User> findByPostTitle(String title);
}
