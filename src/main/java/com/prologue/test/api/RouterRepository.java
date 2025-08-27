package com.prologue.test.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouterRepository extends JpaRepository<Router, Long> {

    Optional<Router> findByEndpoint(String endpoint);
}
