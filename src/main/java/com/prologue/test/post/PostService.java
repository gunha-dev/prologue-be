package com.prologue.test.post;

import com.prologue.test.board.Board;
import com.prologue.test.board.BoardRepository;
import com.prologue.test.config.exception.BadBoardNameException;
import com.prologue.test.config.exception.BadMemberEmailException;
import com.prologue.test.member.Member;
import com.prologue.test.member.MemberRepository;
import com.prologue.test.post.dto.PostCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostCreateRequestDto postCreateRequestDto) {
        String inputMemberEmail = postCreateRequestDto.getMemberEmail();
        Member findMember = memberRepository.findByMemberEmail(inputMemberEmail).orElseThrow(BadMemberEmailException::new);

        String inputBoardName = postCreateRequestDto.getBoardName();
        Board findBoard = boardRepository.findByBoardName(inputBoardName).orElseThrow(BadBoardNameException::new);

        Post createdPost = Post.createPost(findBoard,findMember);
        postRepository.save(createdPost);
        return createdPost;
    }

}
