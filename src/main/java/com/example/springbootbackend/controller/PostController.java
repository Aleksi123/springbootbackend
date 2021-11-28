package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Post;
import com.example.springbootbackend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/posts")
    public @ResponseBody Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping(path="/posts")
    public @ResponseBody Post addNewPost (@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }
}