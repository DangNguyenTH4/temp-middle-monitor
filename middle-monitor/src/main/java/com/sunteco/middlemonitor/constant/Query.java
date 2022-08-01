package com.sunteco.middlemonitor.constant;

import lombok.Getter;

@Getter
public enum Query {
    MEMORY_USED_KB("libvirt_nova_instance_memory_used_kb{criteria}", "{libvirtuuid=\"%s\"}"),
    MEMORY_ALLOCATED_KB("libvirt_nova_instance_memory_alloc_kb{criteria}", "{libvirtuuid=\"%s\"}"),
    TOTAL_VCPU("libvirt_nova_instance_total_vcpu{criteria}", "{libvirtuuid=\"%s\"}"),

    METRIC_CPU_PER_SECOND("irate(libvirt_nova_instance_cpu_time_total{criteria}[30s]){criteria2}", "{libvirtuuid=\"%s\"}"),

    CUSTOM(null, null);
    private final String queryTemplate;
    private final String criteria;
    Query(String queryTemplate, String criteria){
        this.queryTemplate = queryTemplate;
        this.criteria = criteria;
    }
}
