package com.prologue.test.member;

import com.prologue.test.config.exception.BadMemberIdException;
import com.prologue.test.config.exception.BadPasswordException;
import com.prologue.test.member.dto.MemberLoginInputDto;
import com.prologue.test.member.dto.MemberLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member joinMember(String inputId, String inputPassword, String nickname) {
        Member joinMember = Member.createMember(inputId, inputPassword, nickname);
        memberRepository.save(joinMember);
        return joinMember;
    }


    public MemberLoginResponseDto login(MemberLoginInputDto memberLoginInputDto) {
        String inputId = memberLoginInputDto.getInputId();
        Member findMember = memberRepository.findByMemberId(inputId)
                .orElseThrow(BadMemberIdException::new);
        if(!findMember.getPassword().equals(memberLoginInputDto.getInputPassword())) {
            throw new BadPasswordException();
        }
        return new MemberLoginResponseDto(findMember.getMemberId(), findMember.getNickname(), findMember.getRole());
    }
}
