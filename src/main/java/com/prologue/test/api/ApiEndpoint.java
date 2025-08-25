package com.prologue.test.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "api_endpoints")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiEndpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_endpoint_id")
    private Long id;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String endPointName;

    @Column(nullable = false)
    private String endpoint;

    @JoinColumn(name = "micro_service_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MicroService microService;

    public static ApiEndpoint createEndpoint(String method, String endPointName, String endpoint, MicroService microService) {
        return new ApiEndpoint(null, method, endPointName, endpoint, microService);
    }
}
