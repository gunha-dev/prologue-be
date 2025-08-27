package com.prologue.test.admin.kongRouteDto;

import com.prologue.test.admin.kongServiceDto.KongRouteCreateRequestInput;
import com.prologue.test.api.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class KongRouterDto {
    private String serviceName;
    private String routerName;
    private String description;
    private List<String> endpoints;
    private Set<HttpMethod> httpMethod;

    public static KongRouterDto createFromInput(KongRouteCreateRequestInput kongRouteCreateRequestInput) {
        List<String> endpoints = kongRouteCreateRequestInput
                .getRawEndpoints()
                .stream()
                .map(rawEndpoint -> "/" + kongRouteCreateRequestInput.getServiceName() + rawEndpoint)
                .toList();
        return new KongRouterDto(kongRouteCreateRequestInput.getServiceName(),
                kongRouteCreateRequestInput.getRouterName(),
                kongRouteCreateRequestInput.getDescription(),
                endpoints,
                Set.of(kongRouteCreateRequestInput.getHttpMethod()));
    }
}
