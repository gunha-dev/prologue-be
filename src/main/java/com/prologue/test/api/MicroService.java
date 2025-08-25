package com.prologue.test.api;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "micro_service_id")
    private Long id;

    @Column(nullable = false)
    private String domain;

    @Column(nullable = false)
    private String serviceName;

    @OneToMany(mappedBy = "microService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApiEndpoint> endpoint;

    public static MicroService createMicroService(String domain, String microServiceName) {
        return new MicroService(null, domain, microServiceName, new ArrayList<>());
    }
}
