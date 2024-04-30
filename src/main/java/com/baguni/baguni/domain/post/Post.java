package com.baguni.baguni.domain.post;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @NotBlank
    @Column(name = "user_id")
    private UUID userId;

    @CreationTimestamp
    @Column(name = "created_at")
    final private LocalDateTime createdAt = LocalDateTime.now();
}