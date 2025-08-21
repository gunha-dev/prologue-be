package com.prologue.test.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member joinMember(String inputId, String inputPassword) {
        Member joinMember = Member.createMember(inputId, inputPassword);
        memberRepository.save(joinMember);
        return joinMember;
    }

}
