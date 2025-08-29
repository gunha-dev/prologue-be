package com.prologue.test.member.dto;

import com.prologue.test.member.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginResponseDto {
    private String memberId;
    private String memberNickName;
    private MemberRole memberRole;
}
