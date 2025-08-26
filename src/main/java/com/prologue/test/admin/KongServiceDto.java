package com.prologue.test.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KongServiceDto {

    // domain or IP
    private String host;
    
    // 식별값
    private String name;

    // url은
    // {protocol}://{host}:{port}{path}
    private String path;
    private int port;
    private String protocol;

    public KongServiceDto(String host, String name, String path, int port, String protocol) {
        this.host = host;
        this.name = name;
        this.path = path;
        this.port = port;
        this.protocol = protocol;
    }
}
