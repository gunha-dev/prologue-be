package com.prologue.test.api;

import com.prologue.test.admin.KongServiceDto;
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
    public MicroService registerMicroService(KongServiceDto kongServiceDto) {
        if (microServiceRepository.existsByServiceName(kongServiceDto.getName())) {
            throw new IllegalStateException("이미 존재하는 마이크로서비스 이름입니다.");
        }

        MicroService createdMicroService = MicroService.createMicroService(kongServiceDto);
        microServiceRepository.save(createdMicroService);

        eventPublisher.publishEvent(new MicroServiceRegisteredEvent(kongServiceDto));
        return createdMicroService;
    }

    @Transactional
    public RouterEndpoint registerApiEndpoint(ApiEndpointCreateDTO apiEndpointCreateDTO) {
        MicroService microService = microServiceRepository.findById(apiEndpointCreateDTO.getMicroServiceId())
                .orElseThrow(() -> new IllegalArgumentException("해당 MicroService가 존재하지 않습니다."));

        RouterEndpoint createdRouterEndpoint = RouterEndpoint.createEndpoint(
                apiEndpointCreateDTO.getMethod(),
                apiEndpointCreateDTO.getEndPointName(),
                apiEndpointCreateDTO.getEndPoint(),
                microService);

        apiEndPointRepository.save(createdRouterEndpoint);
        return createdRouterEndpoint;
    }
}
