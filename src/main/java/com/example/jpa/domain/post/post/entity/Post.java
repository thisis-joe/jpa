package com.example.jpa.domain.post.post.entity;

import com.example.jpa.domain.post.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedDate;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToMany(mappedBy = "post") // mappedBy를 사용하지 않은 쪽이 주인
    @Builder.Default// mappedBy를 사용하지 않은 쪽이 주인
    private List<Comment> comments = new ArrayList<>();

//    @OneToMany(mappedBy="post",cascade = CascadeType.ALL)
//    @Builder.Default
//    private List<Comment> comments = new ArrayList<>();
//
//    public void addComment(Comment comment) {
//        comments.add(comment);
//
//    }
}