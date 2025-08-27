package com.prologue.test.admin.kongServiceDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class KongServiceDto {

    private String id;

    // domain or IP
    private String host;

    // 식별값
    private String name;

    // url은
    // {protocol}://{host}:{port}{path}
    // path는 /{serviceName}/{version}
    // 으로 구성되어, route에 전파되어야한다
    private String path;

    private int port;
    private String protocol;

    @JsonIgnore
    private String version;

    public KongServiceDto(String host, String name, String path, int port, String protocol, String version) {
        this.host = host;
        this.name = name;
        this.path = path;
        this.port = port;
        this.protocol = protocol;
        this.version = version;
    }

    public static KongServiceDto createFromInput(KongServiceCreateRequestInput kongServiceCreateRequestInput) {
        String generatedPath = kongServiceCreateRequestInput.getPath();
        return new KongServiceDto(kongServiceCreateRequestInput.getHost(), kongServiceCreateRequestInput.getName(),
                generatedPath, kongServiceCreateRequestInput.getPort(), kongServiceCreateRequestInput.getProtocol(),
                kongServiceCreateRequestInput.getVersion());
    }

    public void setId(String id) {
        this.id = id;
    }
}
