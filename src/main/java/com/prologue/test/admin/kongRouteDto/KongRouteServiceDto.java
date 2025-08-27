package com.prologue.test.admin.kongRouteDto;

import lombok.Data;

import java.util.UUID;

@Data
public class KongRouteServiceDto {
    private UUID id;

    public KongRouteServiceDto(UUID id) {
        this.id = id;
    }
}
