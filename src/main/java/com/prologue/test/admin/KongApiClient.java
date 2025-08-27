package com.prologue.test.admin;

import com.prologue.test.admin.kongRouteDto.KongAdminApiRouterDto;
import com.prologue.test.admin.kongRouteDto.KongRouterDto;
import com.prologue.test.admin.kongServiceDto.KongServiceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KongApiClient implements ApiRequest {

    private final OpenFeignKongAdminApi openFeignKongAdminApi;

    @Override
    public void requestKongCP(KongServiceDto kongServiceDto) {
        log.info("kongApiClient.requestKongCP() request kong adminAPI()");
        KongServiceDto result = openFeignKongAdminApi.registerService(kongServiceDto);
    }

    @Override
    public void requestKongCP(KongAdminApiRouterDto kongAdminApiRouterDto) {
        log.info("kongApiClient.requestKongCP() request kong adminAPI()");
        KongServiceDto result = openFeignKongAdminApi.registerRouter(kongAdminApiRouterDto);
    }
}
