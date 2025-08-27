package com.prologue.test.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ApiEndpointCreateDTO {
    private String method;
    private String endPointName;
    private String endPoint;
    private UUID microServiceId;

    public ApiEndpointCreateDTO(String method, String endPointName, String endPoint, UUID microServiceId) {
        this.method = method;
        this.endPointName = endPointName;
        this.endPoint = endPoint;
        this.microServiceId = microServiceId;
    }
}
