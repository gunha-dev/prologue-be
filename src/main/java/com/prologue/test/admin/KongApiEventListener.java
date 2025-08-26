package com.prologue.test.admin;

import com.prologue.test.api.MicroService;
import com.prologue.test.api.MicroServiceRegisteredEvent;
import com.prologue.test.api.MicroServiceRepository;
import com.prologue.test.api.MicroServiceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class KongApiEventListener {

    private final KongApiClient kongApiClient;
    private final MicroServiceRepository microServiceRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleMicroServiceRegistration(MicroServiceRegisteredEvent event) {

        log.info("handleMicroServiceRegistration start");
        MicroService microService = microServiceRepository.findById(event.getMicroServiceId())
                .orElseGet(() -> {
                    log.error("CRITICAL: DB 트랜잭션 commit 후 데이터를 찾을 수 없습니다! microServiceId: {}", event.getMicroServiceId());
                    throw new IllegalStateException("데이터 정합성 오류: 서비스를 찾을 수 없습니다.");
                });

        try {

            // TODO
            log.info("kongApiClient.requestKongCP() call");
            kongApiClient.requestKongCP();

            microService.changeStatus(MicroServiceStatus.ACTIVE);
            log.info("Kong 연동 성공 > Status: ACTIVE / ID: {}", microService.getId());

        } catch (Exception e) {
            log.error("Kong 연동 실패 > Status: FAILED / ID: {}, Error: {}", microService.getId(), e.getMessage());
            microService.changeStatus(MicroServiceStatus.FAILED);
        }
    }

}
