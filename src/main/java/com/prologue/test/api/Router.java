package com.prologue.test.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Router {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "router_id")
    private Long id;

    @Column(nullable = false)
    private HttpMethod method;

    @Column(nullable = false)
    private String endPointName;

    @Column(nullable = false)
    private String endpoint;

    @JoinColumn(name = "micro_service_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MicroService microService;

    @Enumerated(EnumType.STRING)
    private KongAdminApiStatus status;

    private String description;

    public static Router createEndpoint(HttpMethod method, String endPointName, String endpoint, MicroService microService, String description) {
        return new Router(null, method, endPointName, endpoint, microService, KongAdminApiStatus.PENDING, description);
    }

    public void changeAdminApiStatus(KongAdminApiStatus kongAdminApiStatus) {
        this.status = kongAdminApiStatus;
    }

}
