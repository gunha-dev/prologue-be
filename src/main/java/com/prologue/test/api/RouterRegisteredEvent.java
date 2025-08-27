package com.prologue.test.api;

import com.prologue.test.admin.kongRouteDto.KongRouterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RouterRegisteredEvent {
    private final KongRouterDto kongRouterDto;
}
