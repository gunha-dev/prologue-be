package com.prologue.test.temp;

import com.prologue.test.api.ApiEndpointCreateDTO;
import com.prologue.test.api.ApiService;
import com.prologue.test.api.MicroService;
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
        private final ApiService apiService;

        @Transactional
        public void init() {
            for (int i = 1; i <= 5; i++) {
                String inputId = "test" + i;
                String inputPassword = "test" + i;
                memberService.joinMember(inputId, inputPassword);
            }

            String[] domainData = new String[]{"localhost:7777/", "localhost:7778/"};
            MicroService[] microServices = new MicroService[2];

            for (int i = 0; i < 2; i++) {
                String microServiceName = "MicroService" + i;
                microServices[i] = apiService.registerMicroService(domainData[i], microServiceName);
            }

            String[] methodData = new String[]{"GET", "POST", "PUT", "DELETE"};
            String endPointName = "endpoint";
            String endPoint = "api/v1";

            for (int i = 0; i < 2; i++) {
                MicroService microService = microServices[i];
                for (int j = 0; j < 4; j++) {
                    ApiEndpointCreateDTO apiEndpointCreateDTO = new ApiEndpointCreateDTO(methodData[j], endPointName+j, endPoint, microService.getId());
                    apiService.registerApiEndpoint(apiEndpointCreateDTO);
                }
            }
        }
    }

}

