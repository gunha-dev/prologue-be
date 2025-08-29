package com.prologue.test.temp;

import com.prologue.test.api.ApiEndpointCreateDTO;
import com.prologue.test.api.ApiService;
import com.prologue.test.api.MicroService;
import com.prologue.test.api.Protocol;
import com.prologue.test.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitService {

    private final MemberService memberService;
    private final ApiService apiService;

    public void init() {
        log.info("init >>");
        for (int i = 1; i <= 5; i++) {
            String generatedId = "test" + i;
            String generatedPassword = "test" + i;
            String generatedNickname = "nickname"+i;
            memberService.joinMember(generatedId, generatedPassword, generatedNickname);
        }
    }
}