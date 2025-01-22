package com.example.jpa.global;

import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.service.CommentService;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    private final CommentService commentService;
    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            // 데이터가 3개가 이미 있으면 패스
            if( postService.count() > 0 ) {
                return ;
            }

            System.out.println("==== 1번 데이터 생성 ====");
            postService.write("title1", "body1");
            System.out.println("==== 1번 데이터 생성 완료 ====");
            System.out.println("==== 2번 데이터 생성 ====");
            postService.write("title2", "body2");
            System.out.println("==== 2번 데이터 생성 완료 ====");
            System.out.println("==== 3번 데이터 생성 완료 ====");
            postService.write("title3", "body3");
            System.out.println("==== 3번 데이터 생성 완료 ====");

        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return new ApplicationRunner() {
            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                Post post = postService.findById(1L).get();
                if (commentService.count() > 0) {
                    return;
                }
                Comment c5 = Comment.builder()
                        .body("comment5")
                        .build();
                // 2번 방식 -> 훨씬 객체지향적(자바스럽다)
                post.addComment(c5);// comment1 댓글을 세팅
            }
        };
    }
}