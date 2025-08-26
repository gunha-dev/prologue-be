package com.prologue.test.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MicroServiceRegisteredEvent {
    private final Long microServiceId;
    private final String serviceName;
}
