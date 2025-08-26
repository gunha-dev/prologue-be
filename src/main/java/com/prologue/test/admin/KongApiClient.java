package com.prologue.test.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KongApiClient implements ApiRequest {

    // TODO
    @Override
    public void requestKongCP() {
        log.info("kongApiClient.requestKongCP() inner call");
    }
}
