package com.prologue.test.admin.kongRouteDto;

import com.prologue.test.api.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class KongAdminApiRouterDto {

    // 라우터 이름
    private String name;
    private Set<HttpMethod> methods;
    private List<String> paths;
    // 상위 서비스 아이디
    private KongRouteServiceDto service;

    public static KongAdminApiRouterDto createKongAdminApiRouterDto(String name, Set<HttpMethod> methods, List<String> paths, UUID id) {
        return new KongAdminApiRouterDto(name, methods, paths, new KongRouteServiceDto(id));
    }
}
