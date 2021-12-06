package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.PostNotFoundException;
import com.example.springbootbackend.model.Post;
import com.example.springbootbackend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
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
    public Post addNewPost (@RequestBody Post post) {
       return postRepository.save(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post: " + id + "not found."));
        return ResponseEntity.ok(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id , @RequestBody Post newPost){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post: " + id + "not found."));
        post.setTitle(newPost.getTitle());
        post.setBody(newPost.getBody());
        Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post: " + id + "not found."));
        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
