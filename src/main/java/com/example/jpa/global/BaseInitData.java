package com.example.jpa.global;

import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.service.CommentService;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    private final CommentService commentService;
    // 프록시 객체를 획득
    @Autowired
    @Lazy
    private BaseInitData self; // 프록시
    @Transactional
    public void work2() {
        Post post = postService.findById(1L).get();
//        Comment comment = commentService.findById(1L).get();
        int count = post.getComments().size(); //3개
        System.out.println(count);
        post.removeComment(1L);
    }
    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            self.work1();
            self.work2();
        };
    }
    @Transactional
    public void work1() {
        if (postService.count() > 0) {
            return;
        }

        Post p1 = postService.write("title1", "body1");

        Comment c1 = Comment.builder()
                .body("comment1")
                .build();

        c1 = commentService.save(c1);
        p1.addComment(c1);

    }
}