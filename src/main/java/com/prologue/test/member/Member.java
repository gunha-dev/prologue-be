package com.prologue.test.member;

import com.prologue.test.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMBER'")
    private MemberRole role;

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public static Member createMember(String memberEmail, String memberPassword, String nickname){
        return new Member(null, memberEmail, memberPassword, nickname, MemberRole.MEMBER, new ArrayList<>());
    }
}
