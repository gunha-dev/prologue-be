package com.prologue.test.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiService {

    private final MicroServiceRepository microServiceRepository;
    private final ApiEndPointRepository apiEndPointRepository;

    @Transactional
    public MicroService registerMicroService(String domain, String microServiceName) {

        if (microServiceRepository.existsByServiceName(microServiceName)) {
            throw new IllegalStateException("이미 존재하는 마이크로서비스 이름입니다.");
        }

        MicroService createdMicroService = MicroService.createMicroService(domain, microServiceName);
        microServiceRepository.save(createdMicroService);
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
