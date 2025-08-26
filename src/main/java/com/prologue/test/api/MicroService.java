package com.prologue.test.api;

import com.prologue.test.admin.KongServiceDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_services")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @OneToMany(mappedBy = "microService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouterEndpoint> routerEndpoint;

    @Enumerated(EnumType.STRING)
    private MicroServiceStatus status;

    public static MicroService createMicroService(KongServiceDto kongServiceDto) {
        return new MicroService(null, kongServiceDto.getProtocol(), kongServiceDto.getHost(), kongServiceDto.getPort(), kongServiceDto.getPath(), kongServiceDto.getName(), new ArrayList<>(), MicroServiceStatus.PENDING);
    }

    public void changeStatus(MicroServiceStatus microServiceStatus) {
        this.status = microServiceStatus;
    }

    // route의 url은 세부 url, 서로 다른 url
    // {protocol}://{host}:{port}{path} 반환
    public String getMicroServiceUrl() {
        StringBuilder url = new StringBuilder();
        return url.append(this.protocol).append("://").append(this.host).append(":").append(this.port).append(this.path).toString();
    }
}
