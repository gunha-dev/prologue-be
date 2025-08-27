package com.prologue.test.admin;

import com.prologue.test.admin.kongRouteDto.KongAdminApiRouterDto;
import com.prologue.test.admin.kongServiceDto.KongServiceDto;

public interface ApiRequest {
    void requestKongCP(KongServiceDto kongServiceDto);
    void requestKongCP(KongAdminApiRouterDto kongAdminApiRouterDto);
}
