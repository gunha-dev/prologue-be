package com.prologue.test.admin.kongRouteDto;

import lombok.Getter;

import java.util.List;

@Getter
public class KongRoutesResponseDto {
    private List<KongRouteDto> data;
    private String next;
    private String offset;
}
