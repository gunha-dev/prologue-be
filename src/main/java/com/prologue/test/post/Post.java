package com.prologue.test.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prologue.test.audit.BaseAuditEntity;
import com.prologue.test.board.Board;
import com.prologue.test.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @JoinColumn(name="board_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @JoinColumn(name="member_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    
    private String postTitle;

    @Lob
    private String postContent;

    public static Post createPost(Board board, Member author, String title, String content) {
        return new Post(null, board, author, title, content);
    }
}
