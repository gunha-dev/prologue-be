package com.prologue.test.temp;
import com.prologue.test.member.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class tempInitGenerator {

    private final initService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @RequiredArgsConstructor
    private static class initService {

        private final MemberService memberService;

        @Transactional
        public void init() {
            for (int i = 1; i <= 5; i++) {
                String inputId = "test" + i;
                String inputPassword = "test" + i;
                memberService.joinMember(inputId, inputPassword);
            }
        }
    }

}

