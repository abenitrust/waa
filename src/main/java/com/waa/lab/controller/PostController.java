package com.waa.lab.controller;

import com.waa.lab.domain.Post;
import com.waa.lab.domain.dto.PostDto;
import com.waa.lab.service.PostService;
import com.waa.lab.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return postService.getById(id);
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> findByTitle(@PathVariable String title){
        return postService.findByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PostDto p) {
        postService.save(p);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody PostDto p) {
        postService.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        postService.delete(id);
    }

}
