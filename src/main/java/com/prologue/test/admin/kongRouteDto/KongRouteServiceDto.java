package com.prologue.test.admin.kongRouteDto;

import lombok.Data;

@Data
public class KongRouteServiceDto {
    private String id;

    public KongRouteServiceDto(Long id) {
        this.id = id.toString();
    }
}
