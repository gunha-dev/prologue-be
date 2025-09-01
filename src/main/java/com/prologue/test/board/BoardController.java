package com.prologue.test.board;

import com.prologue.test.post.PostService;
import com.prologue.test.post.dto.PostListViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;

    // TODO exception 처리
    @GetMapping("/boards/{boardName}")
    public ResponseEntity<Page<PostListViewDto>> getBoardList(@PathVariable String boardName, @PageableDefault(size=10) Pageable pageable) {
        Page<PostListViewDto> postListViewDto = postService.findPostsByBoardName(boardName, pageable);
        return ResponseEntity.ok(postListViewDto);
    }

}
