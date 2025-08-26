package com.prologue.test.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MicroServiceRepository extends JpaRepository<MicroService, Long> {

    boolean existsByServiceName(String serviceName);
    Optional<MicroService> findByServiceName(String serviceName);
}
