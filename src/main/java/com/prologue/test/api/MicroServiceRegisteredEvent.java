package com.prologue.test.api;

import com.prologue.test.admin.KongServiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MicroServiceRegisteredEvent {
    private final KongServiceDto kongServiceDto;
}
