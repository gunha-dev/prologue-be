package com.prologue.test.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MicroServiceRepository extends JpaRepository<MicroService, Long> {

    boolean existsByServiceName(String serviceName);

}
