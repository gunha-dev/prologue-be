package com.prologue.test.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(String boardName) {
        Board createdBoard = Board.createBoard(boardName);
        boardRepository.save(createdBoard);
        return createdBoard;
    }
}
