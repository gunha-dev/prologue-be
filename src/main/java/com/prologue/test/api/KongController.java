package com.prologue.test.api;

import com.prologue.test.temp.InitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/kong")
@RequiredArgsConstructor
public class KongController {

    private final InitService initService;
    private final ApiService apiService;

    @PostMapping(value = "/admin/microservices")
    public ResponseEntity<MicroService> registerMicroService() {

        String domain = "localhost:7779/";
        String microServiceName = "reqTest1";
        Set<Protocol> protocols = Set.of(Protocol.HTTP, Protocol.HTTPS);

        MicroService createdMicroService = apiService.registerMicroService(domain, microServiceName, protocols);

        return ResponseEntity.accepted().body(createdMicroService);
    }

    @PostMapping(value = "/init")
    public ResponseEntity<String> initMicroService() {

        initService.init();
        return ResponseEntity.ok("SUCCESS");
    }

}
