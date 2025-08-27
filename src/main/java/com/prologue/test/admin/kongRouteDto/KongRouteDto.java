package com.prologue.test.admin.kongRouteDto;

import lombok.Getter;

import java.util.List;

@Getter
public class KongRouteDto {
    private List<String> hosts;
    private String id;
    private String name;
    private String paths;
    private KongRouteServiceDto service;
}
