package com.sunteco.middlemonitor.controller;

import com.sunteco.middlemonitor.constant.Query;
import com.sunteco.middlemonitor.domain.request.CpuQueryRequest;
import com.sunteco.middlemonitor.domain.request.MemoryQueryRequest;
import com.sunteco.middlemonitor.util.QueryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/prometheus")
@Slf4j
public class PrometheusController {
    @Autowired
    private WebClient webClient;
    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/metric/cpu")
    public Mono<Object> metric(@RequestBody CpuQueryRequest cpuQueryRequest) {
        Query query = Query.TOTAL_VCPU;
        String criteria = String.format(query.getCriteria(), cpuQueryRequest.getPlatformUid());
        return webClient.get()
                .uri(QueryBuilder.buildUriQueryRange(query),criteria,cpuQueryRequest.getStart(),
                                cpuQueryRequest.getEnd(),cpuQueryRequest.getStep())
                .retrieve()
                .onStatus(httpStatus -> {
                    return HttpStatus.INTERNAL_SERVER_ERROR == httpStatus;
                }, (clientResponse) -> {
                    log.info(clientResponse.toString());
                    return null;
                })
                .bodyToMono(Object.class);
    }

    @PostMapping("/metric/memory")
    public Mono<Object> memory(@RequestBody MemoryQueryRequest memoryQueryRequest) {
        Query query = Query.MEMORY_USED_KB;
        String criteria = String.format(query.getCriteria(), memoryQueryRequest.getPlatformUid());
        return webClient.get()
                .uri(QueryBuilder.buildUriQueryRange(query),criteria,memoryQueryRequest.getStart(),
                        memoryQueryRequest.getEnd(),memoryQueryRequest.getStep())
                .retrieve()
                .onStatus(httpStatus -> {
                    return HttpStatus.INTERNAL_SERVER_ERROR == httpStatus;
                }, (clientResponse) -> {
                    log.info(clientResponse.toString());
                    return null;
                })
                .bodyToMono(Object.class);
    }

    @PostMapping("/metric/cpu/per-second")
    public Mono<Object> metricCpuPerSecond(@RequestBody CpuQueryRequest cpuQueryRequest) {
        Query query = Query.METRIC_CPU_PER_SECOND;
        String criteria = String.format(query.getCriteria(), cpuQueryRequest.getPlatformUid());
        String criteria2 = "/1e+9";
        return webClient.get()
                .uri(QueryBuilder.buildUriQueryRangeV2(Query.METRIC_CPU_PER_SECOND), cpuQueryRequest.getStart(),
                        cpuQueryRequest.getEnd(),cpuQueryRequest.getStep()
                        ,criteria, criteria2)
                .retrieve()
                .onStatus(httpStatus -> {
                    return HttpStatus.INTERNAL_SERVER_ERROR == httpStatus;
                }, (clientResponse) -> {
                    log.info(clientResponse.toString());
                    return null;
                })
                .bodyToMono(Object.class);
    }

    @PostMapping("/metric/memory/mem-allocated")
    public Mono<Object> memoryAllocated(@RequestBody MemoryQueryRequest memoryQueryRequest) {
        Query query = Query.MEMORY_ALLOCATED_KB;
        String criteria = String.format(query.getCriteria(), memoryQueryRequest.getPlatformUid());
        return webClient.get()
                .uri(QueryBuilder.buildUriQueryRange(query),criteria,memoryQueryRequest.getStart(),
                        memoryQueryRequest.getEnd(),memoryQueryRequest.getStep())
                .retrieve()
                .onStatus(httpStatus -> {
                    return HttpStatus.INTERNAL_SERVER_ERROR == httpStatus;
                }, (clientResponse) -> {
                    log.info(clientResponse.toString());
                    return null;
                })
                .bodyToMono(Object.class);
    }

}
