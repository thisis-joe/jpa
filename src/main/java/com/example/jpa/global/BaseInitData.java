package com.example.jpa.global;

import com.example.jpa.domain.post.post.entitiy.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            // 데이터가 3개가 이미 있으면 패스
            if( postService.count() > 0 ) {
                return ;
            }

            postService.write("title1", "body1");
            postService.write("title2", "body2");
            postService.write("title3", "body3");

            /*
            // 샘플 데이터 3개 생성.
            Post p1 = postService.write("title1", "body1");
            System.out.println(p1.getId() + "번 포스트가 생성되었습니다.");
            Post p2 = postService.write("title2", "body2");
            System.out.println(p2.getId() + "번 포스트가 생성되었습니다.");
            Post p3 = postService.write("title3", "body3");
            System.out.println(p3.getId() + "번 포스트가 생성되었습니다.");
            */
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return args -> {
            Post post = postService.findById(1L).get();
            postService.modify(post, "new title", "new body");
        };
    }
}