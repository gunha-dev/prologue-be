package com.prologue.test.admin.kongServiceDto;

import com.prologue.test.api.HttpMethod;
import lombok.Getter;

import java.util.List;

@Getter
public class KongRouteCreateRequestInput {
    private String serviceName;
    private String routerName;
    private String description;
    private List<String> rawEndpoints;
    private HttpMethod httpMethod;
}
