package com.baguni.baguni.domain.post;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50, message = "제목은 50자를 넘길 수 없습니다.")
    private String title;

    @NotBlank
    @Column(length = 500)
    private String content;

    // 이미지 경로
    private String img1;
    private String img2;
    private String img3;
    private String img4;

    @Column(name = "box_id")
    private Long boxId;

    @NotNull
    @Column(name = "user_id")
    private UUID userId;

    @NotBlank
    @Column(name = "user_nickname")
    private String userNickname;

    @CreationTimestamp
    @Column(name = "created_at")
    final private LocalDateTime createdAt = LocalDateTime.now();

    public Post() {}
    public Post(String title, String content, UUID userId, String userNickname) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.userNickname = userNickname;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", nickname=" + userNickname + "]";
    }
}