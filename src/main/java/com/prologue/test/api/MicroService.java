package com.prologue.test.api;

import com.prologue.test.admin.kongServiceDto.KongServiceDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_services")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MicroService {

    // Kong의 Service
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "micro_service_id")
    private Long id;

    @Column(nullable = false)
    private String protocol;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private int port;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private String version;

    @OneToMany(mappedBy = "microService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Router> router;

    @Enumerated(EnumType.STRING)
    private KongAdminApiStatus status;

    private MicroService(Long id, String protocol, String host, int port, String path, String serviceName, String version, List<Router> router, KongAdminApiStatus kongAdminApiStatus) {
        this.id = id;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
        this.serviceName = serviceName;
        this.version = version;
        this.router = router;
        this.status = kongAdminApiStatus;
    }

    private MicroService(Long id, String protocol, String host, int port, String path, String serviceName, List<Router> router, KongAdminApiStatus status) {
        this.id = id;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
        this.serviceName = serviceName;
        this.version = "v1";
        this.router = router;
        this.status = status;
    }

    public static MicroService createDefaultVersionMicroService(KongServiceDto kongServiceDto) {
        return new MicroService(null, kongServiceDto.getProtocol(), kongServiceDto.getHost(), kongServiceDto.getPort(), kongServiceDto.getPath(), kongServiceDto.getName(),  new ArrayList<>(), KongAdminApiStatus.PENDING);
    }

    public static MicroService createMicroService(KongServiceDto kongServiceDto) {
        return new MicroService(null, kongServiceDto.getProtocol(), kongServiceDto.getHost(), kongServiceDto.getPort(), kongServiceDto.getPath(), kongServiceDto.getName(), kongServiceDto.getVersion(), new ArrayList<>(), KongAdminApiStatus.PENDING);
    }

    public void changeAdminApiStatus(KongAdminApiStatus kongAdminApiStatus) {
        this.status = kongAdminApiStatus;
    }

    // route의 url은 세부 url, 서로 다른 url
    // {protocol}://{host}:{port}{path} 반환
    public String getMicroServiceUrl() {
        return this.protocol + "://" + this.host + ":" + this.port + this.path;
    }
}