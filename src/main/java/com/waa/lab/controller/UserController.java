package com.waa.lab.controller;

import com.waa.lab.annotation.ExecutionTime;
import  com.waa.lab.domain.User;
import com.waa.lab.domain.dto.UserDto;
import com.waa.lab.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return userService.getAll();
    }

    @ExecutionTime
    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @GetMapping("/with_post/{num}")
    public List<User> getUsersWithPosts(@PathVariable int num) {
        return userService.findUserWithPosts(num);
    }

    @GetMapping("/post_title/{title}")
    public List<User> findByPostTitle(@PathVariable String title) {
        return userService.findByPostTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDto u) {
        userService.save(u);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody UserDto u) {
        userService.update(id, u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }



}
