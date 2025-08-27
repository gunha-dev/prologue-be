package com.prologue.test.admin;

import com.prologue.test.admin.kongRouteDto.KongAdminApiRouterDto;
import com.prologue.test.admin.kongRouteDto.KongRouterDto;
import com.prologue.test.admin.kongServiceDto.KongServiceDto;
import com.prologue.test.api.*;
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
    private final RouterRepository routerRepository;
    private final OpenFeignKongAdminApi openFeignKongAdminApi;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleMicroServiceRegistration(MicroServiceRegisteredEvent event) {
        KongServiceDto kongServiceDto = event.getKongServiceDto();
        MicroService findMicroService = microServiceRepository.findByServiceName(kongServiceDto.getName())
                .orElseGet(() -> {
                    log.error("CRITICAL: DB 트랜잭션 commit 후 서비스를 찾을 수 없습니다! microServiceId: {}", kongServiceDto.getName());
                    throw new IllegalStateException("데이터 정합성 오류: 서비스를 찾을 수 없습니다.");
                });

        try {

            kongApiClient.requestKongCP(kongServiceDto);

            findMicroService.changeAdminApiStatus(KongAdminApiStatus.ACTIVE);
            log.info("Kong 연동 성공 > Status: ACTIVE / ID: {}", findMicroService.getId());

        } catch (Exception e) {
            log.error("Kong 연동 실패 > Status: FAILED / ID: {}, Error: {}", findMicroService.getId(), e.getMessage());
            findMicroService.changeAdminApiStatus(KongAdminApiStatus.FAILED);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleRouterRegistration(RouterRegisteredEvent event) {
        KongRouterDto kongRouterDto = event.getKongRouterDto();

        // TODO iteration API 호출 처리해야함
        for (String endpoint : event.getKongRouterDto().getEndpoints() ) {
            Router findRouter = routerRepository.findByEndpoint(endpoint).orElseGet(() -> {
                log.error("CRITICAL: DB 트랜잭션 commit 후 라우터를 찾을 수 없습니다! microServiceId: {}", kongRouterDto.getRouterName());
                throw new IllegalStateException("데이터 정합성 오류: 라우터를 찾을 수 없습니다.");
            });

            try {
                openFeignKongAdminApi.registerRouter(KongAdminApiRouterDto
                        .createKongAdminApiRouterDto(kongRouterDto.getRouterName(),
                                kongRouterDto.getHttpMethod().get(0),
                                kongRouterDto.getEndpoints(),
                                findRouter.getMicroService().getId()));

                findRouter.changeAdminApiStatus(KongAdminApiStatus.ACTIVE);
                log.info("Kong 연동 성공 > Status: ACTIVE / ID: {}", findRouter.getId());

            } catch (Exception e) {
                log.error("Kong 연동 실패 > Status: FAILED / ID: {}, Error: {}", findRouter.getId(), e.getMessage());
                findRouter.changeAdminApiStatus(KongAdminApiStatus.FAILED);
            }
        }
    }

}
