package com.prologue.test.temp;

import com.prologue.test.api.ApiService;
import com.prologue.test.board.Board;
import com.prologue.test.board.BoardService;
import com.prologue.test.member.MemberService;
import com.prologue.test.post.PostService;
import com.prologue.test.post.dto.PostCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitService {

    private final MemberService memberService;
    private final BoardService boardService;
    private final PostService postService;

    public void init() {
        log.info("initService.init() >>");
        for (int i = 1; i <= 5; i++) {
            String generatedId = "test" + i;
            String generatedPassword = "test" + i;
            String generatedNickname = "nickname" + i;
            memberService.joinMember(generatedId, generatedPassword, generatedNickname);
        }

        Board createdBoard = boardService.createBoard("oas");

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                String boardName = createdBoard.getBoardName();
                String memberEmail = "test" + i;
                String title = "title" + j;
                String content = "content" + j;
                postService.createPost(new PostCreateRequestDto(boardName, memberEmail, title, content));
            }
        }
    }
}