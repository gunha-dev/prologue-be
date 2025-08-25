package com.prologue.test.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiEndPointRepository extends JpaRepository<ApiEndpoint, Long> {
}
