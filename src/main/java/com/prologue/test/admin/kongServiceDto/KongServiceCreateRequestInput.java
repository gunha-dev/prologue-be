package com.prologue.test.admin.kongServiceDto;

import lombok.Getter;

@Getter
public class KongServiceCreateRequestInput {
    private String host;
    private String name;
    private String path;
    private int port;
    private String protocol;
    private String version;
}
