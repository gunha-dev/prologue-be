package com.prologue.test.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MicroServiceRepository extends JpaRepository<MicroService, UUID> {

    boolean existsByServiceName(String serviceName);
    Optional<MicroService> findByServiceName(String serviceName);
}
