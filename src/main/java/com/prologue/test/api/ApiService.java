package com.prologue.test.api;

import com.prologue.test.admin.kongRouteDto.KongRouterDto;
import com.prologue.test.admin.kongServiceDto.KongRouteCreateRequestInput;
import com.prologue.test.admin.kongServiceDto.KongServiceCreateRequestInput;
import com.prologue.test.admin.kongServiceDto.KongServiceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ApiService {

    private final MicroServiceRepository microServiceRepository;
    private final RouterRepository routerRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public MicroService registerMicroService(KongServiceCreateRequestInput kongServiceCreateRequestInput) {
        KongServiceDto fromInputDto = KongServiceDto.createFromInput(kongServiceCreateRequestInput);

        if (microServiceRepository.existsByServiceName(fromInputDto.getName())) {
            throw new IllegalStateException("이미 존재하는 마이크로서비스 이름입니다.");
        }

        MicroService createdMicroService;
        String userInputVersion = fromInputDto.getVersion();
        if (StringUtils.hasText(userInputVersion)) {
            createdMicroService = MicroService.createMicroService(fromInputDto);
        } else {
            createdMicroService = MicroService.createDefaultVersionMicroService(fromInputDto);
        }

        microServiceRepository.save(createdMicroService);
        fromInputDto.setId(createdMicroService.getId().toString());
        eventPublisher.publishEvent(new MicroServiceRegisteredEvent(fromInputDto));
        return createdMicroService;
    }

    @Transactional
    public List<Router> registerRouter(KongRouteCreateRequestInput kongRouteCreateRequestInput) {

        KongRouterDto fromInputDto = KongRouterDto.createFromInput(kongRouteCreateRequestInput);

        // 1. DB 저장
        MicroService findMicroService = microServiceRepository.findByServiceName(fromInputDto.getServiceName())
                .orElseThrow(() -> new IllegalArgumentException("해당 MicroService가 존재하지 않습니다."));

        List<Router> routersToSave = fromInputDto.getEndpoints().stream()
                .map(endpoint -> Router.createEndpoint(
                        fromInputDto.getHttpMethod().get(0),
                        fromInputDto.getRouterName(),
                        endpoint,
                        findMicroService,
                        fromInputDto.getDescription()
                ))
                .collect(Collectors.toList());
        routerRepository.saveAll(routersToSave);

        // 2. ADMIN API 호출, 저장
        eventPublisher.publishEvent(new RouterRegisteredEvent(fromInputDto));
        return routersToSave;
    }

//    @Transactional
//    public RouterEndpoint registerApiEndpoint(ApiEndpointCreateDTO apiEndpointCreateDTO) {
//        MicroService microService = microServiceRepository.findById(apiEndpointCreateDTO.getMicroServiceId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 MicroService가 존재하지 않습니다."));
//
//        RouterEndpoint createdRouterEndpoint = RouterEndpoint.createEndpoint(
//                apiEndpointCreateDTO.getMethod(),
//                apiEndpointCreateDTO.getEndPointName(),
//                apiEndpointCreateDTO.getEndPoint(),
//                microService);
//
//        apiEndPointRepository.save(createdRouterEndpoint);
//        return createdRouterEndpoint;
//    }
}
