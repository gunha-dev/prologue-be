package com.prologue.test.admin;

import com.prologue.test.admin.kongRouteDto.KongRoutesResponseDto;
import com.prologue.test.admin.kongServiceDto.KongRouteCreateRequestInput;
import com.prologue.test.admin.kongServiceDto.KongServiceCreateRequestInput;
import com.prologue.test.admin.kongServiceDto.KongServicesResponseDto;
import com.prologue.test.api.ApiService;
import com.prologue.test.api.MicroService;
import com.prologue.test.api.Router;
import com.prologue.test.temp.InitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /* KongServiceCreateRequestInput
       path는 입력값으로 받지 않아야함
    */
    @PostMapping(value = "/microservices")
    public ResponseEntity<MicroService> registerMicroService(@RequestBody KongServiceCreateRequestInput kongServiceCreateRequestInput) {
        MicroService createdMicroService = apiService.registerMicroService(kongServiceCreateRequestInput);
        return ResponseEntity.accepted().body(createdMicroService);
    }

    @DeleteMapping(value = "microservices/{serviceIdOrName}")
    public ResponseEntity<Void> deleteMicroService(@PathVariable String serviceIdOrName) {
        openFeignKongAdminApi.deleteService(serviceIdOrName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/routes")
    public ResponseEntity<KongRoutesResponseDto> getAllRoutes() {
        KongRoutesResponseDto allRoutes = openFeignKongAdminApi.getAllRoutes();
        return ResponseEntity.ok(allRoutes);
    }

    /*
        Service의 name을 받아서 unique한 Service를 검색한다
        Service의 name을 이용해 path를 구성한다
        KongRouteCreateRequestInput
        {

        }
    */
    @PostMapping(value = "/routes")
    public ResponseEntity<List<Router>> registerRoutes(@RequestBody KongRouteCreateRequestInput kongRouteCreateRequestInput) {
        // route db 저장
        List<Router> router = apiService.registerRouter(kongRouteCreateRequestInput);
        return ResponseEntity.accepted().body(router);
    }

    @DeleteMapping(value = "routes/{routesIdOrName}")
    public ResponseEntity<Void> deleteRoutes(@PathVariable String routesIdOrName) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/init")
    public ResponseEntity<String> initMicroService() {
        initService.init();
        return ResponseEntity.noContent().build();
    }
}
