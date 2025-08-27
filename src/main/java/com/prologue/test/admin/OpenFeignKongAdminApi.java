package com.prologue.test.admin;

import com.prologue.test.admin.kongRouteDto.KongAdminApiRouterDto;
import com.prologue.test.admin.kongRouteDto.KongRouterDto;
import com.prologue.test.admin.kongRouteDto.KongRoutesResponseDto;
import com.prologue.test.admin.kongServiceDto.KongServiceDto;
import com.prologue.test.admin.kongServiceDto.KongServicesResponseDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "kong-admin-api", url = "${kong-admin.api.url}")
public interface OpenFeignKongAdminApi {

    @GetMapping("/services")
    KongServicesResponseDto getAllServices();

    @PostMapping("/services")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    KongServiceDto registerService(@RequestBody KongServiceDto kongServiceDto);

    @DeleteMapping("/services/{serviceIdOrName}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    void deleteService(@PathVariable String serviceIdOrName);

    @GetMapping("/routes")
    KongRoutesResponseDto getAllRoutes();

    @PostMapping("/routes")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    KongServiceDto registerRouter(@RequestBody KongAdminApiRouterDto kongAdminApiRouterDto);
}
