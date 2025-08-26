package com.prologue.test.admin;

import com.prologue.test.api.ApiService;
import com.prologue.test.api.MicroService;
import com.prologue.test.temp.InitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kong/admin")
@RequiredArgsConstructor
public class KongController {

    // 연동 테스트용 컨트롤러
    private final InitService initService;
    private final ApiService apiService;
    private final OpenFeignKongAdminApi openFeignKongAdminApi;

    @GetMapping(value = "/microservices")
    public ResponseEntity<KongServicesResponseDto> getAllMicroServices() {
        KongServicesResponseDto allServices = openFeignKongAdminApi.getAllServices();
        return ResponseEntity.ok(allServices);
    }

    @PostMapping(value = "/microservices")
    public ResponseEntity<MicroService> registerMicroService(@RequestBody KongServiceDto kongServiceDto) {
        MicroService createdMicroService = apiService.registerMicroService(kongServiceDto);
        return ResponseEntity.accepted().body(createdMicroService);
    }

    @DeleteMapping(value = "microservices/{serviceIdOrName}")
    public ResponseEntity<String> deleteMicroService(@PathVariable String serviceIdOrName) {
        openFeignKongAdminApi.deleteService(serviceIdOrName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/init")
    public ResponseEntity<String> initMicroService() {

        initService.init();
        return ResponseEntity.noContent().build();
    }

}
