package com.prologue.test.admin;

import lombok.Getter;

import java.util.List;

@Getter
public class KongServicesResponseDto {
    private List<KongServiceDto> data;
    private String next;
    private String offset;
}
