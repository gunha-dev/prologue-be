package com.prologue.test.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String domain;

    @Column(nullable = false)
    private String serviceName;

    @OneToMany(mappedBy = "microService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApiEndpoint> endpoint;

    @Enumerated(EnumType.STRING)
    private MicroServiceStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "micro_service_protocols",
            joinColumns = @JoinColumn(name = "micro_service_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "protocol")
    private Set<Protocol> protocols;

    public static MicroService createMicroService(String domain, String microServiceName, Set<Protocol> protocols) {
        return new MicroService(null, domain, microServiceName, new ArrayList<>(), MicroServiceStatus.PENDING, protocols);
    }

    public void changeStatus(MicroServiceStatus microServiceStatus) {
        this.status = microServiceStatus;
    }
}
