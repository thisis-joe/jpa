package com.example.jpa.domain.post.post.repository;

import com.example.jpa.domain.post.post.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
