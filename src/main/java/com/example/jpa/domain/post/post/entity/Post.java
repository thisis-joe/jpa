package com.example.jpa.domain.post.post.entity;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.tag.entity.Tag;
import com.example.jpa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post extends BaseTime {

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default// mappedBy를 사용하지 않은 쪽이 주인
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<Tag> tags = new ArrayList<>();

    public void addComment(Comment c1) {
        comments.add(c1);
        c1.setPost(this);
    }
    public void removeComment(Comment c1) {
        comments.remove(c1);
    }
    public void removeComment(long id) {
        Optional<Comment> opComment = comments.stream()
                .filter(com -> com.getId() == id)
                .findFirst();
        opComment.ifPresent(comment -> comments.remove(comment));
    }
    public void removeAllComments() {
        comments
                .forEach(comment -> {
                    comment.setPost(null);
                });
        comments.clear();
    }
    public void addTag(String name) {
        Tag tag = Tag.builder()
                .name(name)
                .post(this)
                .build();

        tags.add(tag);
    }

}