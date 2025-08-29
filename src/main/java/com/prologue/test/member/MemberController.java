package com.prologue.test.member;

import com.prologue.test.member.dto.MemberLoginInputDto;
import com.prologue.test.member.dto.MemberLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> memberLogin(@RequestBody MemberLoginInputDto memberLoginInputDto) {
        MemberLoginResponseDto responseDto = memberService.login(memberLoginInputDto);
        return ResponseEntity.ok(responseDto);
    }
}