package com.prologue.test.temp;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class tempInitGenerator {

    private final InitService initService; // 분리된 InitService를 주입

//    @PostConstruct
//    public void init() {
//        initService.init();
//    }
}