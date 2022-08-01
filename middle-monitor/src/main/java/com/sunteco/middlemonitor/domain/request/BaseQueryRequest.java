package com.sunteco.middlemonitor.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseQueryRequest {
    private String step;
    private Long start;
    private Long end;
}
