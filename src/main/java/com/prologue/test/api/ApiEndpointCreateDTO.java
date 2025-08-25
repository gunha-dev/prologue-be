package com.prologue.test.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiEndpointCreateDTO {
    private String method;
    private String endPointName;
    private String endPoint;
    private Long microServiceId;

    public ApiEndpointCreateDTO(String method, String endPointName, String endPoint, Long microServiceId) {
        this.method = method;
        this.endPointName = endPointName;
        this.endPoint = endPoint;
        this.microServiceId = microServiceId;
    }
}
