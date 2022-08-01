package com.sunteco.middlemonitor.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpuQueryRequest extends BaseQueryRequest {
    private String platformUid;
}
