package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
