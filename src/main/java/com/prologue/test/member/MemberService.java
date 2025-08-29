package com.prologue.test.member;

import com.prologue.test.config.exception.BadMemberEmailException;
import com.prologue.test.config.exception.BadPasswordException;
import com.prologue.test.member.dto.MemberLoginInputDto;
import com.prologue.test.member.dto.MemberLoginResponseDto;
import lombok.RequiredArgsConstructor;
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
        Member findMember = memberRepository.findByMemberEmail(inputId)
                .orElseThrow(BadMemberEmailException::new);
        if(!findMember.getPassword().equals(memberLoginInputDto.getInputPassword())) {
            throw new BadPasswordException();
        }
        return new MemberLoginResponseDto(findMember.getMemberEmail(), findMember.getNickname(), findMember.getRole());
    }
}
