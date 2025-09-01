package com.prologue.test.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostListViewDto {
    private Long postId;
    private String postTitle;
    private String memberEmail;
    private LocalDateTime createdAt;
}
