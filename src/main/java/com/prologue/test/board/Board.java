package com.prologue.test.board;

import com.prologue.test.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="boards")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;

    @Column(unique = true)
    private String boardName;

    @OneToMany(mappedBy = "board" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public static Board createBoard(String boardName) {
        return new Board(null, boardName, new ArrayList<>());
    }
}
