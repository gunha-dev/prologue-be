package com.prologue.test.post;

import com.prologue.test.post.dto.PostListViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new com.prologue.test.post.dto.PostListViewDto(p.id, p.postTitle, m.memberEmail, p.createdDate) " +
            "from Post p join p.board b join p.member m " +
            "where b.boardName = :boardName")
    Page<PostListViewDto> findByBoardName(@Param("boardName") String boardName, Pageable pageable);
}