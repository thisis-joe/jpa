package com.example.jpa.domain.post.post.repository;

import com.example.jpa.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);
    List<Post> findByTitleAndBody(String title, String body);
    List<Post> findByTitleLike(String keyword);
    List<Post> findByOrderByIdDesc();
    List<Post> findTop2ByTitleOrderByIdDesc(String title);
}