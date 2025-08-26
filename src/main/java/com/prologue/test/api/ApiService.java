package com.prologue.test.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ApiService {

    private final MicroServiceRepository microServiceRepository;
    private final ApiEndPointRepository apiEndPointRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public MicroService registerMicroService(String domain, String microServiceName, Set<Protocol> protocols) {

        log.info("registerMicroService start");
        if (microServiceRepository.existsByServiceName(microServiceName)) {
            throw new IllegalStateException("이미 존재하는 마이크로서비스 이름입니다.");
        }

        MicroService createdMicroService = MicroService.createMicroService(domain, microServiceName, protocols);
        microServiceRepository.save(createdMicroService);

        // TODO
        log.info("eventPublisher.publishEvent start");
        eventPublisher.publishEvent(new MicroServiceRegisteredEvent(
                createdMicroService.getId(),
                createdMicroService.getServiceName()
        ));
        log.info("eventPublisher.publishEvent end");

        return createdMicroService;
    }

    @Transactional
    public ApiEndpoint registerApiEndpoint(ApiEndpointCreateDTO apiEndpointCreateDTO) {
        MicroService microService = microServiceRepository.findById(apiEndpointCreateDTO.getMicroServiceId())
                .orElseThrow(() -> new IllegalArgumentException("해당 MicroService가 존재하지 않습니다."));

        ApiEndpoint createdApiEndpoint = ApiEndpoint.createEndpoint(
                apiEndpointCreateDTO.getMethod(),
                apiEndpointCreateDTO.getEndPointName(),
                apiEndpointCreateDTO.getEndPoint(),
                microService);

        apiEndPointRepository.save(createdApiEndpoint);
        return createdApiEndpoint;
    }


}
