package com.prologue.test.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreateRequestDto {
    private String boardName;
    private String memberEmail;
    private String title;
    private String content;
}
